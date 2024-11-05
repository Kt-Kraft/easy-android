package feature.cameraView.screen

import feature.cameraView.model.Flash
import easy.camera.state.CameraState

public interface CameraContract {

  public data class UiState(
    public val enableTorch: Boolean = false,
    public val supportedFlash: List<Flash> = listOf(Flash.Off, Flash.On, Flash.Auto),
  )

  public sealed interface UiAction {
    public data object OnCameraPermissionRequested : UiAction
    public data object OnCameraPermissionPermitted : UiAction
    public data object OnSwitchFlash : UiAction
    public data object OnSwitchTorch : UiAction
    public data class OnTakePicture(val cameraState: CameraState) : UiAction
  }

  public sealed interface SideEffect {
    public data class ShowSnackBar(val message: Int) : SideEffect
  }
}
