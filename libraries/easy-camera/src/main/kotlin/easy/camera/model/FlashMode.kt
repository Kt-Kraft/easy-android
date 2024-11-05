package easy.camera.model

import androidx.camera.core.ImageCapture

public enum class FlashMode(internal val mode: Int) {
  On(ImageCapture.FLASH_MODE_ON),
  Auto(ImageCapture.FLASH_MODE_AUTO),
  Off(ImageCapture.FLASH_MODE_OFF);

  public val inverse: FlashMode
    get() = when (this) {
      On -> Off
      else -> On
    }

  internal companion object {
    internal fun find(mode: Int) = entries.firstOrNull { it.mode == mode } ?: Off
  }
}
