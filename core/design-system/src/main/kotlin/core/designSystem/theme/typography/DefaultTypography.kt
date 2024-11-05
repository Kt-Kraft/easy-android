package core.designSystem.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val DefaultTypography = Typography(
  displayLarge = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 57.sp,
    lineHeight = 64.sp,
    letterSpacing = (-0.25).sp,
  ),
  displayMedium = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 45.sp,
    lineHeight = 52.sp,
  ),
  displaySmall = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 36.sp,
    lineHeight = 44.sp,
  ),
  headlineLarge = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 32.sp,
    lineHeight = 40.sp,
  ),
  headlineMedium = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 28.sp,
    lineHeight = 36.sp,
  ),
  headlineSmall = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 24.sp,
    lineHeight = 32.sp,
  ),
  titleLarge = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W700,
    fontSize = 22.sp,
    lineHeight = 28.sp,
  ),
  titleMedium = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W700,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.1.sp,
  ),
  titleSmall = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp,
  ),
  bodyLarge = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
  ),
  bodyMedium = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp,
  ),
  bodySmall = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp,
  ),
  labelLarge = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp,
  ),
  labelMedium = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp,
  ),
  labelSmall = TextStyle(
    fontFamily = jakartaSansFamily,
    fontWeight = FontWeight.W500,
    fontSize = 10.sp,
    lineHeight = 16.sp,
  ),
)
