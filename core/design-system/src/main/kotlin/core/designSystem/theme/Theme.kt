package core.designSystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import core.designSystem.theme.color.MaterialDarkColorScheme
import core.designSystem.theme.color.MaterialLightColorScheme
import core.designSystem.theme.color.model.AppColorSchemeModel
import core.designSystem.theme.typography.DefaultTypography
import core.designSystem.theme.typography.model.AppTypographyModel

@Composable
public fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val appTypography = AppTypographyModel()
  val appColorScheme = if (darkTheme) {
    AppColorSchemeModel.darkColorScheme()
  } else {
    AppColorSchemeModel.lightColorScheme()
  }

  val materialColorScheme = if (darkTheme) {
    MaterialDarkColorScheme
  } else {
    MaterialLightColorScheme
  }

  CompositionLocalProvider(
    LocalColorScheme provides appColorScheme,
    LocalTypography provides appTypography,
  ) {
    MaterialTheme(
      colorScheme = materialColorScheme,
      typography = DefaultTypography,
      content = content,
    )
  }
}
