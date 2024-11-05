package easy.camera.model

import androidx.camera.core.ImageAnalysis

public enum class ImageAnalysisBackpressureStrategy(internal val strategy: Int) {
  KeepOnlyLatest(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST),
  BlockProducer(ImageAnalysis.STRATEGY_BLOCK_PRODUCER);

  internal companion object {
    internal fun find(strategy: Int) =
      entries.firstOrNull { it.strategy == strategy } ?: KeepOnlyLatest
  }
}
