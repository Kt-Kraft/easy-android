package easy.camera.state

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.camera.core.CameraEffect
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.MeteringPoint
import androidx.camera.core.TorchState
import androidx.camera.view.CameraController.IMAGE_ANALYSIS
import androidx.camera.view.CameraController.OutputSize
import androidx.camera.view.CameraController.OutputSize.UNASSIGNED_ASPECT_RATIO
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File
import java.util.concurrent.Executor
import easy.camera.model.CamSelector
import easy.camera.model.CaptureMode
import easy.camera.model.FlashMode
import easy.camera.model.ImageCaptureMode
import easy.camera.model.ImageTargetSize
import easy.camera.model.ImplementationMode
import easy.camera.model.ScaleType
import easy.camera.extensions.compatMainExecutor
import easy.camera.extensions.isImageAnalysisSupported

@Stable
public class CameraState(context: Context) {

  private val mainExecutor: Executor = context.compatMainExecutor
  private val contentResolver: ContentResolver = context.contentResolver

  private val MeteringPoint.isFocusMeteringSupported: Boolean
    get() = controller.cameraInfo?.isFocusMeteringSupported(
      FocusMeteringAction.Builder(this).build(),
    ) ?: false

  public val controller: LifecycleCameraController = LifecycleCameraController(context)

  public var maxZoom: Float by mutableFloatStateOf(
    controller.zoomState.value?.maxZoomRatio ?: INITIAL_ZOOM_VALUE,
  )
    internal set

  public var minZoom: Float by mutableFloatStateOf(
    controller.zoomState.value?.minZoomRatio ?: INITIAL_ZOOM_VALUE,
  )
    internal set

  private val exposureCompensationRange
    get() = controller.cameraInfo?.exposureState?.exposureCompensationRange

  public var minExposure: Int by mutableIntStateOf(
    exposureCompensationRange?.lower ?: INITIAL_EXPOSURE_VALUE,
  )
    internal set

  public var maxExposure: Int by mutableIntStateOf(
    exposureCompensationRange?.upper ?: INITIAL_EXPOSURE_VALUE,
  )
    internal set

  public val initialExposure: Int = INITIAL_EXPOSURE_VALUE
    get() = controller.cameraInfo?.exposureState?.exposureCompensationIndex ?: field

  public val isExposureSupported: Boolean by derivedStateOf { maxExposure != INITIAL_EXPOSURE_VALUE }

  public var isStreaming: Boolean by mutableStateOf(false)
    internal set

  public val isZoomSupported: Boolean by derivedStateOf { maxZoom != 1F }

  public var isFocusOnTapSupported: Boolean by mutableStateOf(true)

  public var isInitialized: Boolean by mutableStateOf(false)
    internal set

  public var hasFlashUnit: Boolean by mutableStateOf(
    controller.cameraInfo?.hasFlashUnit() ?: true,
  )

  internal var captureMode: CaptureMode = CaptureMode.Image
    set(value) {
      if (field != value) {
        field = value
        updateCaptureMode()
      }
    }

  internal var imageCaptureMode: ImageCaptureMode = ImageCaptureMode.MinLatency
    set(value) {
      if (field != value) {
        field = value
        controller.imageCaptureMode = value.mode
      }
    }

  internal var scaleType: ScaleType = ScaleType.FillCenter

  internal var implementationMode: ImplementationMode = ImplementationMode.Performance

  internal var camSelector: CamSelector = CamSelector.Back
    set(value) {
      when {
        value == field -> Unit

        hasCamera(value) -> {
          if (controller.cameraSelector != value.selector) {
            controller.cameraSelector = value.selector
            field = value
            resetCamera()
          }
        }

        else -> Log.e(TAG, "Device does not have ${value.selector} camera")
      }
    }

  internal var imageCaptureTargetSize: ImageTargetSize?
    get() = controller.imageCaptureTargetSize.toImageTargetSize()
    set(value) {
      if (value != imageCaptureTargetSize) {
        controller.imageCaptureTargetSize = value?.toOutputSize()
      }
    }

  private var imageAnalyzer: ImageAnalysis.Analyzer? = null
    set(value) {
      field = value
      updateImageAnalyzer(value)
    }

  private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as? CameraManager

  public var isImageAnalysisSupported: Boolean by mutableStateOf(
    isImageAnalysisSupported(
      camSelector,
    ),
  )
    private set

  internal var isImageAnalysisEnabled: Boolean = isImageAnalysisSupported
    set(value) {
      if (!isImageAnalysisSupported) {
        Log.e(TAG, "Image analysis is not supported")
        return
      }
      field = value
      updateCaptureMode()
    }

  internal var imageAnalysisBackpressureStrategy: Int
    get() = controller.imageAnalysisBackpressureStrategy
    set(value) {
      if (imageAnalysisBackpressureStrategy != value) {
        controller.imageAnalysisBackpressureStrategy = value
      }
    }

  internal var imageAnalysisTargetSize: OutputSize?
    get() = controller.imageAnalysisTargetSize
    set(value) {
      if (imageAnalysisTargetSize != value) {
        controller.imageAnalysisTargetSize = value
      }
    }

  internal var imageAnalysisImageQueueDepth: Int
    get() = controller.imageAnalysisImageQueueDepth
    set(value) {
      if (imageAnalysisImageQueueDepth != value) {
        controller.imageAnalysisImageQueueDepth = value
      }
    }

  internal var isFocusOnTapEnabled: Boolean
    get() = controller.isTapToFocusEnabled
    set(value) {
      controller.isTapToFocusEnabled = value
    }

  internal var flashMode: FlashMode
    get() = FlashMode.find(controller.imageCaptureFlashMode)
    set(value) {
      if (hasFlashUnit && flashMode != value) {
        controller.imageCaptureFlashMode = value.mode
      }
    }

  public var enableTorch: Boolean
    get() = controller.torchState.value == TorchState.ON
    set(value) {
      if (enableTorch != value) {
        controller.enableTorch(hasFlashUnit && value)
      }
    }

  init {
    controller.initializationFuture.addListener(
      {
        resetCamera()
        isInitialized = true
      },
      mainExecutor,
    )
  }

  private fun updateCaptureMode() {
    try {
      var useCases = captureMode.value
      if (captureMode == CaptureMode.Image && isImageAnalysisEnabled) {
        useCases = useCases or IMAGE_ANALYSIS
        updateImageAnalyzer(imageAnalyzer)
      } else {
        updateImageAnalyzer(null)
      }
      controller.setEnabledUseCases(useCases)
    } catch (exception: IllegalStateException) {
      Log.e(TAG, "Use case Image Analysis not supported")
      controller.setEnabledUseCases(captureMode.value)
    }
  }

  private fun updateImageAnalyzer(
    analyzer: ImageAnalysis.Analyzer? = imageAnalyzer,
  ) = with(controller) {
    clearImageAnalysisAnalyzer()
    setImageAnalysisAnalyzer(mainExecutor, analyzer ?: return)
  }

  private fun startExposure() {
    minExposure = exposureCompensationRange?.lower ?: INITIAL_EXPOSURE_VALUE
    maxExposure = exposureCompensationRange?.upper ?: INITIAL_EXPOSURE_VALUE
  }

  public fun takePicture(
    contentValues: ContentValues,
    saveCollection: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
    onResult: (ImageCaptureResult) -> Unit,
  ) {
    takePicture(
      outputFileOptions = ImageCapture.OutputFileOptions.Builder(
        contentResolver, saveCollection, contentValues,
      ).build(),
      onResult = onResult,
    )
  }

  public fun takePicture(
    file: File, onResult: (ImageCaptureResult) -> Unit,
  ) {
    takePicture(ImageCapture.OutputFileOptions.Builder(file).build(), onResult)
  }

  public fun takePicture(
    outputFileOptions: ImageCapture.OutputFileOptions,
    onResult: (ImageCaptureResult) -> Unit,
  ) {
    try {
      controller.takePicture(
        outputFileOptions,
        mainExecutor,
        object : ImageCapture.OnImageSavedCallback {
          override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            onResult(ImageCaptureResult.Success(outputFileResults.savedUri))
          }

          override fun onError(exception: ImageCaptureException) {
            onResult(ImageCaptureResult.Error(exception))
          }
        },
      )
    } catch (exception: Exception) {
      onResult(ImageCaptureResult.Error(exception))
    }
  }

  private fun setExposureCompensation(exposureCompensation: Int) {
    controller.cameraControl?.setExposureCompensationIndex(exposureCompensation)
  }

  public fun hasCamera(cameraSelector: CamSelector): Boolean =
    isInitialized && controller.hasCamera(cameraSelector.selector)

  private fun startZoom() {
    controller.isPinchToZoomEnabled = true
    val zoom = controller.zoomState.value
    minZoom = zoom?.minZoomRatio ?: INITIAL_ZOOM_VALUE
    maxZoom = zoom?.maxZoomRatio ?: INITIAL_ZOOM_VALUE
  }

  private fun resetCamera() {
    hasFlashUnit = controller.cameraInfo?.hasFlashUnit() ?: false
    isImageAnalysisSupported = isImageAnalysisSupported(camSelector)
    startZoom()
    startExposure()
  }

  public fun setEffects(effects: Set<CameraEffect>) {
    controller.setEffects(effects)
  }

  public fun clearEffects() {
    controller.clearEffects()
  }

  @SuppressLint("RestrictedApi")
  @VisibleForTesting
  internal fun isImageAnalysisSupported(
    cameraSelector: CamSelector = camSelector,
  ): Boolean = cameraManager?.isImageAnalysisSupported(
    cameraSelector.selector.lensFacing,
  ) ?: false

  internal fun update(
    camSelector: CamSelector,
    captureMode: CaptureMode,
    scaleType: ScaleType,
    imageCaptureTargetSize: ImageTargetSize?,
    isImageAnalysisEnabled: Boolean,
    imageAnalyzer: ImageAnalyzer?,
    implementationMode: ImplementationMode,
    isFocusOnTapEnabled: Boolean,
    flashMode: FlashMode,
    imageCaptureMode: ImageCaptureMode,
    enableTorch: Boolean,
    meteringPoint: MeteringPoint,
    exposureCompensation: Int,
  ) {
    this.camSelector = camSelector
    this.captureMode = captureMode
    this.scaleType = scaleType
    this.imageCaptureTargetSize = imageCaptureTargetSize
    this.isImageAnalysisEnabled = isImageAnalysisEnabled
    this.imageAnalyzer = imageAnalyzer?.analyzer
    this.implementationMode = implementationMode
    this.isFocusOnTapEnabled = isFocusOnTapEnabled
    this.flashMode = flashMode
    this.enableTorch = enableTorch
    this.isFocusOnTapSupported = meteringPoint.isFocusMeteringSupported
    this.imageCaptureMode = imageCaptureMode
    setExposureCompensation(exposureCompensation)
  }

  private companion object {
    private val TAG = this::class.java.name
    private const val INITIAL_ZOOM_VALUE = 1F
    private const val INITIAL_EXPOSURE_VALUE = 0
  }
}

private fun OutputSize?.toImageTargetSize(): ImageTargetSize? {
  return this?.let {
    if (it.aspectRatio != UNASSIGNED_ASPECT_RATIO) {
      ImageTargetSize(aspectRatio = it.aspectRatio)
    } else {
      ImageTargetSize(size = it.resolution)
    }
  }
}
