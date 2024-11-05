package core.designSystem.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import core.designSystem.R

public val jakartaSansFamily: FontFamily = FontFamily(
  Font(R.font.plus_jakarta_sans_light, FontWeight.Light),
  Font(R.font.plus_jakarta_sans_regular, FontWeight.Normal),
  Font(R.font.plus_jakarta_sans_italic, FontWeight.Normal, FontStyle.Italic),
  Font(R.font.plus_jakarta_sans_medium, FontWeight.Medium),
  Font(R.font.plus_jakarta_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
  Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold),
  Font(R.font.plus_jakarta_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
  Font(R.font.plus_jakarta_sans_semi_bold, FontWeight.SemiBold),
  Font(R.font.plus_jakarta_sans_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
  Font(R.font.plus_jakarta_sans_extra_bold, FontWeight.ExtraBold),
)

internal object AppTypographyTokens {
  val metadata10Bold = TextStyle(
    fontWeight = FontWeight.W700,
    fontSize = 10.sp,
    lineHeight = 16.sp,
    fontFamily = jakartaSansFamily,
  )

  val metadata10Regular = TextStyle(
    fontWeight = FontWeight.W400,
    fontSize = 10.sp,
    lineHeight = 16.sp,
    fontFamily = jakartaSansFamily,
  )

  val metadata12Bold = TextStyle(
    fontWeight = FontWeight.W700,
    fontSize = 12.sp,
    lineHeight = 18.sp,
    fontFamily = jakartaSansFamily,
  )

  val metadata12Regular = TextStyle(
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = 18.sp,
    fontFamily = jakartaSansFamily,
  )

  val body14ExtraBold = TextStyle(
    fontWeight = FontWeight.W900,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontFamily = jakartaSansFamily,
  )

  val body14Bold = TextStyle(
    fontWeight = FontWeight.W700,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontFamily = jakartaSansFamily,
  )

  val body14Regular = TextStyle(
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    fontFamily = jakartaSansFamily,
  )

  val body16Bold = TextStyle(
    fontWeight = FontWeight.W700,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    fontFamily = jakartaSansFamily,
  )

  val body16Regular = TextStyle(
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    fontFamily = jakartaSansFamily,
  )

  val header18Bold = TextStyle(
    fontWeight = FontWeight.W700,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    fontFamily = jakartaSansFamily,
  )

  val header18Regular = TextStyle(
    fontWeight = FontWeight.W400,
    fontSize = 18.sp,
    lineHeight = 28.sp,
    fontFamily = jakartaSansFamily,
  )
}
