package easy.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import feature.homepage.navigation.homepageNavigation

@Composable
public fun MainNavGraph(
  startDestination: Any,
  navController: NavHostController,
) {
  NavHost(
    navController = navController,
    startDestination = startDestination,
  ) {
    homepageNavigation()
  }
}
