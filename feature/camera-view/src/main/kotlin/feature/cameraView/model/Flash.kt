package feature.cameraView.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import feature.cameraView.R
import easy.camera.model.FlashMode

public enum class Flash(
  @DrawableRes public val drawableRes: Int,
  @StringRes public val contentRes: Int,
) {
  Off(R.drawable.flash_off, R.string.flash_off),
  On(R.drawable.flash_on, R.string.flash_on),
  Auto(R.drawable.flash_auto, R.string.flash_auto)
}

public fun Flash.toFlashMode(): FlashMode = when (this) {
  Flash.Auto -> FlashMode.Auto
  Flash.On -> FlashMode.On
  Flash.Off -> FlashMode.Off
}

public fun FlashMode.toFlash(): Flash = when (this) {
  FlashMode.On -> Flash.On
  FlashMode.Auto -> Flash.Auto
  FlashMode.Off -> Flash.Off
}
