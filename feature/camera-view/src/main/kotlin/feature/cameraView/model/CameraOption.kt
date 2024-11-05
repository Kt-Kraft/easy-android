package feature.cameraView.model

import androidx.annotation.StringRes
import easy.camera.model.CaptureMode
import feature.cameraView.R

public enum class CameraOption(@StringRes public val titleRes: Int) {
  Photo(R.string.photo),
  QRCode(R.string.qr_code);

  public fun toCaptureMode(): CaptureMode = when (this) {
    QRCode, Photo -> CaptureMode.Image
  }
}
