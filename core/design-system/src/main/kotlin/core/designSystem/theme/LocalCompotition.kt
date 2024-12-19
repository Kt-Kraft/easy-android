package core.designSystem.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import core.designSystem.theme.color.model.AppColorSchemeModel
import core.designSystem.theme.typography.model.AppTypographyModel

public val LocalColorScheme: ProvidableCompositionLocal<AppColorSchemeModel> =
  staticCompositionLocalOf { AppColorSchemeModel() }

public val LocalTypography: ProvidableCompositionLocal<AppTypographyModel> =
  staticCompositionLocalOf { AppTypographyModel() }
