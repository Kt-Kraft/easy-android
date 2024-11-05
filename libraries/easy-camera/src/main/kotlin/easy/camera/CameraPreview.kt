package easy.camera

import androidx.camera.core.ZoomState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import easy.camera.model.CamSelector
import easy.camera.model.CaptureMode
import easy.camera.model.FlashMode
import easy.camera.model.ImageCaptureMode
import easy.camera.model.ImageTargetSize
import easy.camera.model.ImplementationMode
import easy.camera.model.ScaleType
import easy.camera.focus.SquareCornerFocus
import easy.camera.state.CameraState
import easy.camera.state.ImageAnalyzer
import easy.camera.state.rememberCameraState

@Composable
public fun CameraPreview(
  modifier: Modifier = Modifier,
  cameraState: CameraState = rememberCameraState(),
  camSelector: CamSelector = cameraState.camSelector,
  captureMode: CaptureMode = cameraState.captureMode,
  imageCaptureMode: ImageCaptureMode = cameraState.imageCaptureMode,
  imageCaptureTargetSize: ImageTargetSize? = cameraState.imageCaptureTargetSize,
  flashMode: FlashMode = cameraState.flashMode,
  scaleType: ScaleType = cameraState.scaleType,
  enableTorch: Boolean = cameraState.enableTorch,
  exposureCompensation: Int = cameraState.initialExposure,
  imageAnalyzer: ImageAnalyzer? = null,
  implementationMode: ImplementationMode = cameraState.implementationMode,
  isImageAnalysisEnabled: Boolean = cameraState.isImageAnalysisEnabled,
  isFocusOnTapEnabled: Boolean = cameraState.isFocusOnTapEnabled,
  isPinchToZoomEnabled: Boolean = cameraState.isZoomSupported,
  onFocus: suspend (onComplete: () -> Unit) -> Unit = { onComplete ->
    delay(1000L)
    onComplete()
  },
  onZoomRatioChanged: (ZoomState) -> Unit = {},
  focusTapContent: @Composable () -> Unit = { SquareCornerFocus() },
  content: @Composable () -> Unit = {},
) {
  CameraPreviewImpl(
    modifier = modifier,
    cameraState = cameraState,
    camSelector = camSelector,
    captureMode = captureMode,
    exposureCompensation = exposureCompensation,
    imageCaptureMode = imageCaptureMode,
    imageCaptureTargetSize = imageCaptureTargetSize,
    flashMode = flashMode,
    scaleType = scaleType,
    enableTorch = enableTorch,
    imageAnalyzer = imageAnalyzer,
    isImageAnalysisEnabled = isImageAnalysisEnabled,
    implementationMode = implementationMode,
    isFocusOnTapEnabled = isFocusOnTapEnabled,
    isPinchToZoomEnabled = isPinchToZoomEnabled,
    onZoomRatioChanged = onZoomRatioChanged,
    focusTapContent = focusTapContent,
    onFocus = onFocus,
    content = content,
  )
}
