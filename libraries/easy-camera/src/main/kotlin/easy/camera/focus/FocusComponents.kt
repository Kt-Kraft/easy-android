package easy.camera.focus

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private class CornerBorder(
  drawScope: DrawScope,
  val x: Float,
  val y: Float,
  val thicknessDp: Dp,
  val brush: Brush,
  val borderSize: Dp,
) {

  val thickness: Float
  val borderAdjust: Float
  val cornerSize: Float

  val cornerStartX: Float
  val cornerEndX: Float

  val cornerStartY: Float
  val cornerEndY: Float

  init {
    with(drawScope) {
      thickness = thicknessDp.value * density
      borderAdjust = thickness / ADJUST_THICKNESS_HALF_SIZE

      cornerSize = when {
        borderSize != Dp.Unspecified -> borderSize.value * density
        else -> x / DEFAULT_SIZE
      }

      cornerStartX = x - cornerSize
      cornerStartY = y - cornerSize
      cornerEndX = x - borderAdjust
      cornerEndY = y - borderAdjust

      drawBottomStartCornerLine()
      drawBottomEndCornerLine()
      drawTopStartCornerLine()
      drawTopEndCornerLine()
    }
  }

  private fun DrawScope.drawTopEndCornerLine() {
    drawLine(brush, Offset(cornerStartX, 0f), Offset(x, 0f), thickness)
    drawLine(brush, Offset(cornerEndX, 0f), Offset(cornerEndX, cornerSize), thickness)
  }

  private fun DrawScope.drawTopStartCornerLine() {
    drawLine(brush, Offset(0f, 0f), Offset(cornerSize, 0f), thickness)
    drawLine(brush, Offset(borderAdjust, 0f), Offset(borderAdjust, cornerSize), thickness)
  }

  private fun DrawScope.drawBottomEndCornerLine() {
    drawLine(brush, Offset(cornerStartX, y), Offset(x, y), thickness)
    drawLine(brush, Offset(cornerEndX, y), Offset(cornerEndX, cornerStartY), thickness)
  }

  private fun DrawScope.drawBottomStartCornerLine() {
    drawLine(brush, Offset(0f, y), Offset(cornerSize, y), thickness)
    drawLine(brush, Offset(borderAdjust, y), Offset(borderAdjust, cornerStartY), thickness)
  }

  companion object {
    private const val DEFAULT_SIZE = 4
    private const val ADJUST_THICKNESS_HALF_SIZE = 2
  }
}

internal fun DrawScope.drawCornerBorder(brush: Brush, x: Float, y: Float, thickness: Dp, borderSize: Dp) {
  CornerBorder(this, x, y, thickness, brush, borderSize)
}

@Composable
public fun SquareCornerFocus(
  modifier: Modifier = Modifier,
  tapSize: Dp = DefaultFocusSize,
  borderSize: Dp = Dp.Unspecified,
  borderStroke: BorderStroke = DefaultBorderStroke,
) {
  val scaleAnim by scaleAsState()
  Box(
    Modifier
      .size(tapSize)
      .scale(scaleAnim)
      .drawBehind {
        drawCornerBorder(
          brush = borderStroke.brush,
          x = size.width,
          y = size.height,
          thickness = borderStroke.width,
          borderSize = borderSize,
        )
      }
      .then(modifier),
  )
}

@Composable
internal fun scaleAsState(
  initialValue: Float = 1.5F,
  targetValue: Float = 1F,
  animationSpec: AnimationSpec<Float>? = null,
): State<Float> {
  var scale by remember { mutableFloatStateOf(initialValue) }
  LaunchedEffect(scale) { scale = targetValue }
  return animateFloatAsState(
    targetValue = scale,
    animationSpec = animationSpec ?: tween(easing = LinearOutSlowInEasing),
    label = "ScaleAsState",
  )
}

private val DefaultFocusSize = 64.dp
private val DefaultBorderStroke = BorderStroke(2.dp, Color.White)
