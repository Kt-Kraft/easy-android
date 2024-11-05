package easy.camera.extensions

import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import java.io.File
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine
import easy.camera.state.CameraState
import easy.camera.state.ImageCaptureResult

public suspend fun CameraState.takePicture(file: File): Uri? =
  suspendCancellableCoroutine { continuation ->
    with(continuation) { takePicture(file, ::takePictureContinuation) }
  }

public suspend fun CameraState.takePicture(
  contentValues: ContentValues,
  saveCollection: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
): Uri? =
  suspendCancellableCoroutine { continuation ->
    with(continuation) { takePicture(contentValues, saveCollection, ::takePictureContinuation) }
  }

public suspend fun CameraState.takePicture(
  outputFileOptions: ImageCapture.OutputFileOptions,
): Uri? =
  suspendCancellableCoroutine { continuation ->
    with(continuation) { takePicture(outputFileOptions, ::takePictureContinuation) }
  }

private fun Continuation<Uri?>.takePictureContinuation(result: ImageCaptureResult) {
  when (val res: ImageCaptureResult = result) {
    is ImageCaptureResult.Error -> resumeWithException(res.throwable)
    is ImageCaptureResult.Success -> resume(res.savedUri)
  }
}
