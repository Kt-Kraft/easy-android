package feature.cameraView.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import feature.cameraView.components.FlashButton
import feature.cameraView.components.PictureButton
import feature.cameraView.components.SwitchButton
import feature.cameraView.model.Flash
import feature.cameraView.model.toFlashMode
import easy.camera.CameraPreview
import easy.camera.model.CamSelector
import easy.camera.state.rememberCameraSelector

@Composable
public fun CameraScreen(
  uiState: CameraContract.UiState,
  sideEffect: Flow<CameraContract.SideEffect>,
  onAction: (CameraContract.UiAction) -> Unit,
) {
  val snackBarHostState = remember { SnackbarHostState() }
  var camSelector by rememberCameraSelector(CamSelector.Back)

  Scaffold(
    snackbarHost = { SnackbarHost(snackBarHostState) },
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize(),
    ) {
      CameraPreview(
        modifier = Modifier.fillMaxSize(),
        camSelector = camSelector,
        enableTorch = uiState.enableTorch,
        flashMode = uiState.supportedFlash.first().toFlashMode(),
      )
      Column(
        modifier = Modifier.align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(24.dp),
      ) {
        CameraActions(
          flashMode = uiState.supportedFlash.first(),
          onSwitchCamera = { camSelector = camSelector.inverse },
          onSwitchFlash = { onAction(CameraContract.UiAction.OnSwitchFlash) },
        )
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .background(
              color = Color(0xFF05010C),
              shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            ),
        )
      }
    }
  }
}

@Composable
public fun CameraActions(
  flashMode: Flash,
  onSwitchCamera: () -> Unit,
  onSwitchFlash: () -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(24.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      FlashButton(flash = flashMode, onClick = { onSwitchFlash() })
      PictureButton(
        isEnable = true,
        onClick = { },
      )
      SwitchButton(onClick = onSwitchCamera)
    }
  }
}
