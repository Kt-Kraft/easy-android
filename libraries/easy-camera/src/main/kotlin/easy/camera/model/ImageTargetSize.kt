package easy.camera.model

import android.util.Size
import androidx.camera.core.AspectRatio
import androidx.camera.view.CameraController.OutputSize

public data class ImageTargetSize(
  private var aspectRatio: Int? = null,
  private var size: Size? = null,
  private var outputSize: OutputSize? = null,
) {

  public constructor(@AspectRatio.Ratio aspectRatio: Int?) : this(
    aspectRatio = aspectRatio,
    size = null,
    outputSize = null,
  )

  public constructor(size: Size?) : this(
    aspectRatio = null,
    size = size,
    outputSize = null,
  )

  internal constructor(outputSize: OutputSize?) : this(
    aspectRatio = null,
    size = null,
    outputSize = outputSize,
  )

  internal fun toOutputSize(): OutputSize? {
    return outputSize ?: aspectRatio?.let { OutputSize(it) } ?: size?.let { OutputSize(it) }
  }
}
