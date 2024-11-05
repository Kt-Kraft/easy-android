package easy.camera.model

import androidx.camera.view.CameraController.IMAGE_CAPTURE

public enum class CaptureMode(
  internal val value: Int,
) {
  Image(IMAGE_CAPTURE)
}
