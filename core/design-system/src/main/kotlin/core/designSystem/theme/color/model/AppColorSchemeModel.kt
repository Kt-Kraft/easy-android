package core.designSystem.theme.color.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import core.designSystem.theme.color.backgroundPrimaryDark
import core.designSystem.theme.color.backgroundPrimaryLight
import core.designSystem.theme.color.backgroundSecondaryDark
import core.designSystem.theme.color.backgroundSecondaryLight
import core.designSystem.theme.color.backgroundTertiaryDark
import core.designSystem.theme.color.backgroundTertiaryLight
import core.designSystem.theme.color.borderCriticalDark
import core.designSystem.theme.color.borderCriticalLight
import core.designSystem.theme.color.borderPrimaryDark
import core.designSystem.theme.color.borderPrimaryLight
import core.designSystem.theme.color.borderSecondaryDark
import core.designSystem.theme.color.borderSecondaryLight
import core.designSystem.theme.color.borderSelectedDark
import core.designSystem.theme.color.borderSelectedLight
import core.designSystem.theme.color.borderTertiaryDark
import core.designSystem.theme.color.borderTertiaryLight
import core.designSystem.theme.color.borderWarningDark
import core.designSystem.theme.color.borderWarningLight
import core.designSystem.theme.color.brandColorDark
import core.designSystem.theme.color.brandColorLight
import core.designSystem.theme.color.decorativeBlueDark
import core.designSystem.theme.color.decorativeBlueLight
import core.designSystem.theme.color.decorativeBlueVariantDark
import core.designSystem.theme.color.decorativeBlueVariantLight
import core.designSystem.theme.color.decorativeRedDark
import core.designSystem.theme.color.decorativeRedLight
import core.designSystem.theme.color.decorativeRedVariantDark
import core.designSystem.theme.color.decorativeRedVariantLight
import core.designSystem.theme.color.decorativeYellowDark
import core.designSystem.theme.color.decorativeYellowLight
import core.designSystem.theme.color.decorativeYellowVariantDark
import core.designSystem.theme.color.decorativeYellowVariantLight
import core.designSystem.theme.color.interactiveCriticalDark
import core.designSystem.theme.color.interactiveCriticalLight
import core.designSystem.theme.color.interactiveCriticalSubduedDark
import core.designSystem.theme.color.interactiveCriticalSubduedLight
import core.designSystem.theme.color.interactiveDefaultDark
import core.designSystem.theme.color.interactiveDefaultLight
import core.designSystem.theme.color.interactiveDisabledDark
import core.designSystem.theme.color.interactiveDisabledLight
import core.designSystem.theme.color.interactiveDisabledSubduedDark
import core.designSystem.theme.color.interactiveDisabledSubduedLight
import core.designSystem.theme.color.interactiveEnabledDark
import core.designSystem.theme.color.interactiveEnabledLight
import core.designSystem.theme.color.interactiveHoverDark
import core.designSystem.theme.color.interactiveHoverLight
import core.designSystem.theme.color.interactivePressedDark
import core.designSystem.theme.color.interactivePressedLight
import core.designSystem.theme.color.interactiveSelectedDark
import core.designSystem.theme.color.interactiveSelectedLight
import core.designSystem.theme.color.statusErrorDark
import core.designSystem.theme.color.statusErrorLight
import core.designSystem.theme.color.statusSuccessDark
import core.designSystem.theme.color.statusSuccessLight
import core.designSystem.theme.color.statusWarningDark
import core.designSystem.theme.color.statusWarningLight
import core.designSystem.theme.color.surfaceCriticalDark
import core.designSystem.theme.color.surfaceCriticalLight
import core.designSystem.theme.color.surfaceInverseDark
import core.designSystem.theme.color.surfaceInverseLight
import core.designSystem.theme.color.surfacePrimaryDark
import core.designSystem.theme.color.surfacePrimaryLight
import core.designSystem.theme.color.surfaceSecondaryDark
import core.designSystem.theme.color.surfaceSecondaryLight
import core.designSystem.theme.color.surfaceWarningDark
import core.designSystem.theme.color.surfaceWarningLight
import core.designSystem.theme.color.textCriticalDark
import core.designSystem.theme.color.textCriticalLight
import core.designSystem.theme.color.textInverseDark
import core.designSystem.theme.color.textInverseLight
import core.designSystem.theme.color.textPrimaryDark
import core.designSystem.theme.color.textPrimaryLight
import core.designSystem.theme.color.textSecondaryDark
import core.designSystem.theme.color.textSecondaryLight
import core.designSystem.theme.color.textSubduedDark
import core.designSystem.theme.color.textSubduedLight
import core.designSystem.theme.color.textTertiaryDark
import core.designSystem.theme.color.textTertiaryLight
import core.designSystem.theme.color.textWarningDark
import core.designSystem.theme.color.textWarningLight

@Immutable
public data class AppColorSchemeModel(
  val brandColor: Color = brandColorLight,
  val backgroundPrimary: Color = backgroundPrimaryLight,
  val backgroundSecondary: Color = backgroundSecondaryLight,
  val backgroundTertiary: Color = backgroundTertiaryLight,
  val surfacePrimary: Color = surfacePrimaryLight,
  val surfaceSecondary: Color = surfaceSecondaryLight,
  val surfaceWarning: Color = surfaceWarningLight,
  val surfaceCritical: Color = surfaceCriticalLight,
  val surfaceInverse: Color = surfaceInverseLight,
  val textPrimary: Color = textPrimaryLight,
  val textSecondary: Color = textSecondaryLight,
  val textTertiary: Color = textTertiaryLight,
  val textSubdued: Color = textSubduedLight,
  val textWarning: Color = textWarningLight,
  val textCritical: Color = textCriticalLight,
  val textInverse: Color = textInverseLight,
  val borderPrimary: Color = borderPrimaryLight,
  val borderSecondary: Color = borderSecondaryLight,
  val borderTertiary: Color = borderTertiaryLight,
  val borderSelected: Color = borderSelectedLight,
  val borderWarning: Color = borderWarningLight,
  val borderCritical: Color = borderCriticalLight,
  val interactiveDefault: Color = interactiveDefaultLight,
  val interactiveEnabled: Color = interactiveEnabledLight,
  val interactiveHover: Color = interactiveHoverLight,
  val interactivePressed: Color = interactivePressedLight,
  val interactiveSelected: Color = interactiveSelectedLight,
  val interactiveCritical: Color = interactiveCriticalLight,
  val interactiveCriticalSubdued: Color = interactiveCriticalSubduedLight,
  val interactiveDisabled: Color = interactiveDisabledLight,
  val interactiveDisabledSubdued: Color = interactiveDisabledSubduedLight,
  val statusSuccess: Color = statusSuccessLight,
  val statusWarning: Color = statusWarningLight,
  val statusError: Color = statusErrorLight,
  val decorativeBlue: Color = decorativeBlueLight,
  val decorativeBlueVariant: Color = decorativeBlueVariantLight,
  val decorativeRed: Color = decorativeRedLight,
  val decorativeRedVariant: Color = decorativeRedVariantLight,
  val decorativeYellow: Color = decorativeYellowLight,
  val decorativeYellowVariant: Color = decorativeYellowVariantLight,
) {
  public companion object {
    public fun lightColorScheme(): AppColorSchemeModel {
      return AppColorSchemeModel()
    }

    public fun darkColorScheme(): AppColorSchemeModel {
      return AppColorSchemeModel(
        brandColor = brandColorDark,
        backgroundPrimary = backgroundPrimaryDark,
        backgroundSecondary = backgroundSecondaryDark,
        backgroundTertiary = backgroundTertiaryDark,
        surfacePrimary = surfacePrimaryDark,
        surfaceSecondary = surfaceSecondaryDark,
        surfaceWarning = surfaceWarningDark,
        surfaceCritical = surfaceCriticalDark,
        surfaceInverse = surfaceInverseDark,
        textPrimary = textPrimaryDark,
        textSecondary = textSecondaryDark,
        textTertiary = textTertiaryDark,
        textSubdued = textSubduedDark,
        textWarning = textWarningDark,
        textCritical = textCriticalDark,
        textInverse = textInverseDark,
        borderPrimary = borderPrimaryDark,
        borderSecondary = borderSecondaryDark,
        borderTertiary = borderTertiaryDark,
        borderSelected = borderSelectedDark,
        borderWarning = borderWarningDark,
        borderCritical = borderCriticalDark,
        interactiveDefault = interactiveDefaultDark,
        interactiveEnabled = interactiveEnabledDark,
        interactiveHover = interactiveHoverDark,
        interactivePressed = interactivePressedDark,
        interactiveSelected = interactiveSelectedDark,
        interactiveCritical = interactiveCriticalDark,
        interactiveCriticalSubdued = interactiveCriticalSubduedDark,
        interactiveDisabled = interactiveDisabledDark,
        interactiveDisabledSubdued = interactiveDisabledSubduedDark,
        statusSuccess = statusSuccessDark,
        statusWarning = statusWarningDark,
        statusError = statusErrorDark,
        decorativeBlue = decorativeBlueDark,
        decorativeBlueVariant = decorativeBlueVariantDark,
        decorativeRed = decorativeRedDark,
        decorativeRedVariant = decorativeRedVariantDark,
        decorativeYellow = decorativeYellowDark,
        decorativeYellowVariant = decorativeYellowVariantDark,
      )
    }
  }
}
