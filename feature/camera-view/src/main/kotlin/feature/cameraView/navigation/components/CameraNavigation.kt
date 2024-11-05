package feature.cameraView.navigation.components

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.navigation.Screen
import feature.cameraView.screen.CameraDestination

public fun NavGraphBuilder.cameraNavigation() {
  composable<Screen.CameraView> {
    CameraDestination()
  }
}
