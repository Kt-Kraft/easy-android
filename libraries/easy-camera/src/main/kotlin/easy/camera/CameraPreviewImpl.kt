package easy.camera

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.camera.core.ZoomState
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import easy.camera.model.CamSelector
import easy.camera.model.CaptureMode
import easy.camera.model.FlashMode
import easy.camera.model.ImageCaptureMode
import easy.camera.model.ImageTargetSize
import easy.camera.model.ImplementationMode
import easy.camera.model.ScaleType
import easy.camera.focus.FocusTap
import easy.camera.state.CameraState
import easy.camera.state.ImageAnalyzer

@Composable
internal fun CameraPreviewImpl(
  modifier: Modifier,
  cameraState: CameraState,
  camSelector: CamSelector,
  captureMode: CaptureMode,
  imageCaptureMode: ImageCaptureMode,
  imageCaptureTargetSize: ImageTargetSize?,
  flashMode: FlashMode,
  scaleType: ScaleType,
  enableTorch: Boolean,
  implementationMode: ImplementationMode,
  imageAnalyzer: ImageAnalyzer?,
  exposureCompensation: Int,
  isImageAnalysisEnabled: Boolean,
  isFocusOnTapEnabled: Boolean,
  isPinchToZoomEnabled: Boolean,
  onZoomRatioChanged: (ZoomState) -> Unit,
  onFocus: suspend (() -> Unit) -> Unit,
  focusTapContent: @Composable () -> Unit,
  content: @Composable () -> Unit,
) {
  val lifecycleOwner = LocalLifecycleOwner.current
  val isCameraInitialized by rememberUpdatedState(cameraState.isInitialized)
  var tapOffset by remember { mutableStateOf(Offset.Zero) }

  val cameraZoomState by cameraState.controller.zoomState.observeAsState()
  LaunchedEffect(cameraZoomState) {
    cameraZoomState?.let { onZoomRatioChanged(it) }
  }

  AndroidView(
    modifier = modifier,
    factory = { context ->
      PreviewView(context).apply {
        layoutParams = ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT,
        )

        controller = cameraState.controller.apply {
          bindToLifecycle(lifecycleOwner)
        }

        previewStreamState.observe(lifecycleOwner) {
          cameraState.isStreaming = it == PreviewView.StreamState.STREAMING
        }

        setOnTouchListener { view, event ->
          GestureDetector(
            context,
            object : GestureDetector.SimpleOnGestureListener() {
              override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
                tapOffset = Offset(event.x, event.y)
                view.performClick()
                return true
              }
            },
          ).onTouchEvent(event)
          onTouchEvent(event)
        }
      }
    },
    update = { preview ->
      if (isCameraInitialized) {
        with(preview) {
          this.scaleType = scaleType.type
          this.implementationMode = implementationMode.value
          cameraState.update(
            camSelector = camSelector,
            captureMode = captureMode,
            imageCaptureTargetSize = imageCaptureTargetSize,
            scaleType = scaleType,
            isImageAnalysisEnabled = isImageAnalysisEnabled,
            imageAnalyzer = imageAnalyzer,
            implementationMode = implementationMode,
            isFocusOnTapEnabled = isFocusOnTapEnabled,
            flashMode = flashMode,
            enableTorch = enableTorch,
            imageCaptureMode = imageCaptureMode,
            meteringPoint = meteringPointFactory.createPoint(x, y),
            exposureCompensation = exposureCompensation,
          )
        }
      }
    },
  )

  FocusTap(
    offset = tapOffset,
    onFocus = { onFocus { tapOffset = Offset.Zero } },
  ) { focusTapContent() }

  content()
}
