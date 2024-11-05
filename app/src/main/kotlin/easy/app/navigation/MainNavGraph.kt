package easy.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import feature.cameraView.navigation.cameraViewNavigation

@Composable
public fun MainNavGraph(
  startDestination: Any,
  navController: NavHostController,
) {
  NavHost(
    modifier = Modifier
      .background(Color(0xFF0B021C))
      .windowInsetsPadding(WindowInsets.navigationBars),
    navController = navController,
    startDestination = startDestination,
  ) {
    cameraViewNavigation()
  }
}
