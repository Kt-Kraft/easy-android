package easy.camera.focus

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.layout
import kotlin.math.roundToInt

@Composable
internal fun FocusTap(
  modifier: Modifier = Modifier,
  offset: Offset,
  onFocus: suspend () -> Unit = {},
  focusContent: @Composable () -> Unit = {},
) {
  val isFocused by remember(offset) { derivedStateOf { offset != Offset.Zero } }
  if (isFocused) {
    val focusMovable = remember(offset) {
      movableContentOf {
        Box(
          Modifier
            .then(modifier)
            .layout { measurable, constraints ->
              val placeable = measurable.measure(constraints)
              layout(placeable.width, placeable.height) {
                val relativeX = offset.x.roundToInt() - placeable.width / 2
                val relativeY = offset.y.roundToInt() - placeable.height / 2
                placeable.placeRelative(relativeX, relativeY)
              }
            },
        ) {
          focusContent()
        }
      }
    }
    focusMovable()
    LaunchedEffect(offset) { onFocus() }
  }
}
