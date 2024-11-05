package feature.cameraView.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import easy.mvi.unpack

@Composable
public fun CameraDestination(
  viewModel: CameraViewModel = viewModel(),
) {
  val (uiState, onAction, sideEffect) = viewModel.unpack()
  CameraScreen(uiState, sideEffect, onAction)
}
