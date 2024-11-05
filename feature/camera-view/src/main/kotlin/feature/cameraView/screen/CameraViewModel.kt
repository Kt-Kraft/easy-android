package feature.cameraView.screen

import androidx.lifecycle.ViewModel
import core.common.rotate
import easy.mvi.MVI
import easy.mvi.mvi

public class CameraViewModel : ViewModel(),
  MVI<CameraContract.UiState, CameraContract.UiAction, CameraContract.SideEffect> by mvi(
    CameraContract.UiState(),
  ) {

  override fun onAction(uiAction: CameraContract.UiAction) {
    when (uiAction) {
      CameraContract.UiAction.OnCameraPermissionPermitted -> {}

      CameraContract.UiAction.OnCameraPermissionRequested -> {}

      CameraContract.UiAction.OnSwitchTorch -> {
        updateUiState { copy(enableTorch = !enableTorch) }
      }

      CameraContract.UiAction.OnSwitchFlash -> {
        updateUiState { copy(supportedFlash = supportedFlash.rotate(1)) }
      }

      is CameraContract.UiAction.OnTakePicture -> {}

    }
  }
}
