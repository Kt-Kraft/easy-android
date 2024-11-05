package core.designSystem.theme.typography.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import core.designSystem.theme.typography.AppTypographyTokens

@Immutable
public data class AppTypographyModel(
  val metadata10Bold: TextStyle = AppTypographyTokens.metadata10Bold,
  val metadata10Regular: TextStyle = AppTypographyTokens.metadata10Regular,
  val metadata12Bold: TextStyle = AppTypographyTokens.metadata12Bold,
  val metadata12Regular: TextStyle = AppTypographyTokens.metadata12Regular,
  val body14ExtraBold: TextStyle = AppTypographyTokens.body14ExtraBold,
  val body14Bold: TextStyle = AppTypographyTokens.body14Bold,
  val body14Regular: TextStyle = AppTypographyTokens.body14Regular,
  val body16Bold: TextStyle = AppTypographyTokens.body16Bold,
  val body16Regular: TextStyle = AppTypographyTokens.body16Regular,
  val header18Bold: TextStyle = AppTypographyTokens.header18Bold,
  val header18Regular: TextStyle = AppTypographyTokens.header18Regular,
)
