package easy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import core.designSystem.theme.AppTheme
import core.navigation.Screen
import easy.app.navigation.MainNavGraph

public class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    val splashScreen = installSplashScreen()
    super.onCreate(savedInstanceState)

    splashScreen.setKeepOnScreenCondition {
      false
    }

    enableEdgeToEdge(
      SystemBarStyle.dark(getColor(R.color.background)),
      SystemBarStyle.dark(getColor(R.color.background)),
    )

    setContent {
      val navController = rememberNavController()
      AppTheme {
        MainNavGraph(
          navController = navController,
          startDestination = Screen.Homepage
        )
      }
    }
  }
}
