package easy.camera.state

import androidx.camera.core.ImageAnalysis
import androidx.compose.runtime.Stable
import easy.camera.model.ImageAnalysisBackpressureStrategy
import easy.camera.model.ImageTargetSize

@Stable
public class ImageAnalyzer(
  private val cameraState: CameraState,
  imageAnalysisBackpressureStrategy: ImageAnalysisBackpressureStrategy,
  imageAnalysisTargetSize: ImageTargetSize?,
  imageAnalysisImageQueueDepth: Int,
  internal var analyzer: ImageAnalysis.Analyzer,
) {

  init {
    updateCameraState(
      imageAnalysisBackpressureStrategy,
      imageAnalysisTargetSize,
      imageAnalysisImageQueueDepth,
    )
  }

  private fun updateCameraState(
    imageAnalysisBackpressureStrategy: ImageAnalysisBackpressureStrategy,
    imageAnalysisTargetSize: ImageTargetSize?,
    imageAnalysisImageQueueDepth: Int,
  ) = with(cameraState) {
    this.imageAnalysisBackpressureStrategy = imageAnalysisBackpressureStrategy.strategy
    this.imageAnalysisTargetSize = imageAnalysisTargetSize?.toOutputSize()
    this.imageAnalysisImageQueueDepth = imageAnalysisImageQueueDepth
  }

  public fun update(
    imageAnalysisBackpressureStrategy: ImageAnalysisBackpressureStrategy = ImageAnalysisBackpressureStrategy.find(
      cameraState.imageAnalysisBackpressureStrategy,
    ),
    imageAnalysisTargetSize: ImageTargetSize? = ImageTargetSize(cameraState.imageAnalysisTargetSize),
    imageAnalysisImageQueueDepth: Int = cameraState.imageAnalysisImageQueueDepth,
    analyzer: ImageAnalysis.Analyzer = this.analyzer,
  ) {
    updateCameraState(
      imageAnalysisBackpressureStrategy,
      imageAnalysisTargetSize,
      imageAnalysisImageQueueDepth,
    )
    this.analyzer = analyzer
  }
}
