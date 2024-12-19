package feature.homepage.navigation.components

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.navigation.Screen
import feature.homepage.screen.dashboard.HomepageScreenDestination

public fun NavGraphBuilder.dashboardNavigation() {
  composable<Screen.Homepage> {
    HomepageScreenDestination()
  }
}
