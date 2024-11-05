package easy.camera.state

import androidx.camera.core.ImageAnalysis
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import easy.camera.model.CamSelector
import easy.camera.model.FlashMode
import easy.camera.model.ImageAnalysisBackpressureStrategy
import easy.camera.model.ImageTargetSize

@Composable
public fun rememberCameraState(): CameraState {
  val context = LocalContext.current
  return remember { CameraState(context) }
}

@Composable
public fun rememberCameraSelector(
  selector: CamSelector = CamSelector.Back,
): MutableState<CamSelector> = rememberSaveable(saver = CamSelector.Saver) {
  mutableStateOf(selector)
}

@Composable
public fun CameraState.rememberFlashMode(
  initialFlashMode: FlashMode = FlashMode.Off,
  useSaver: Boolean = true,
): MutableState<FlashMode> = rememberConditionalState(
  initialValue = initialFlashMode,
  defaultValue = FlashMode.Off,
  useSaver = useSaver,
  predicate = hasFlashUnit,
)

@Composable
public fun CameraState.rememberTorch(
  initialTorch: Boolean = false,
  useSaver: Boolean = true,
): MutableState<Boolean> = rememberConditionalState(
  initialValue = initialTorch,
  defaultValue = false,
  useSaver = useSaver,
  predicate = hasFlashUnit,
)

@Composable
public fun CameraState.rememberImageAnalyzer(
  imageAnalysisBackpressureStrategy: ImageAnalysisBackpressureStrategy = ImageAnalysisBackpressureStrategy.KeepOnlyLatest,
  imageAnalysisTargetSize: ImageTargetSize? = ImageTargetSize(this.imageAnalysisTargetSize),
  imageAnalysisImageQueueDepth: Int = this.imageAnalysisImageQueueDepth,
  analyze: ImageAnalysis.Analyzer,
): ImageAnalyzer = remember(this) {
  ImageAnalyzer(
    this,
    imageAnalysisBackpressureStrategy,
    imageAnalysisTargetSize,
    imageAnalysisImageQueueDepth,
    analyze,
  )
}
