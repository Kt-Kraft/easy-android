package easy.camera.state

import android.net.Uri
import androidx.compose.runtime.Immutable

public sealed interface ImageCaptureResult {
  @Immutable
  public data class Success(val savedUri: Uri?) : ImageCaptureResult

  @Immutable
  public data class Error(val throwable: Throwable) : ImageCaptureResult
}
