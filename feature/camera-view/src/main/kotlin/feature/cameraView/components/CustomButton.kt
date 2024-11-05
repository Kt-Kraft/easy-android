package feature.cameraView.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import feature.cameraView.R
import feature.cameraView.model.Flash

@Composable
public fun SwitchButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  var clicked by remember { mutableStateOf(false) }
  val rotate by animateFloatAsState(
    targetValue = if (clicked) 360F else 1F,
    animationSpec = tween(durationMillis = 500), label = "AnimateFloat",
  )
  CustomButton(
    modifier = Modifier
      .rotate(rotate)
      .size(48.dp)
      .background(Color(0xCC333333), CircleShape)
      .clip(CircleShape)
      .then(modifier),
    onClick = {
      clicked = !clicked
      onClick()
    },
  ) {
    Image(
      modifier = Modifier.size(24.dp),
      painter = painterResource(id = R.drawable.refresh),
      colorFilter = ColorFilter.tint(Color.White),
      contentDescription = stringResource(R.string.refresh),
    )
  }
}

@Composable
public fun PictureButton(
  modifier: Modifier = Modifier,
  isEnable: Boolean = true,
  onClick: () -> Unit,
) {
  val color by animateColorAsState(
    targetValue = Color.White,
    animationSpec = tween(durationMillis = 250), label = "AnimateColor",
  )
  val innerPadding by animateDpAsState(targetValue = 8.dp, label = "AnimateDp")
  val percentShape by animateIntAsState(targetValue = 50, label = "AnimateInt")
  Box(
    contentAlignment = Alignment.Center,
  ) {
    CustomButton(
      modifier = Modifier
        .size(80.dp)
        .border(BorderStroke(4.dp, color), CircleShape)
        .padding(innerPadding)
        .background(color, RoundedCornerShape(percentShape))
        .clip(CircleShape)
        .then(modifier),
      onClick = onClick,
      enabled = isEnable,
    )
    if (!isEnable) {
      Image(
        modifier = Modifier
          .size(32.dp),
        imageVector = Icons.Default.Lock,
        contentDescription = null,
      )
    }
  }
}

@Composable
public fun FlashButton(
  modifier: Modifier = Modifier,
  flash: Flash,
  onClick: () -> Unit,
) {
  CustomButton(
    modifier = Modifier
      .size(48.dp)
      .background(Color(0xCC333333), CircleShape)
      .clip(CircleShape)
      .then(modifier),
    onClick = onClick,
  ) {
    Image(
      modifier = Modifier
        .size(24.dp),
      painter = painterResource(flash.drawableRes),
      contentDescription = stringResource(flash.contentRes),
    )
  }
}

@Composable
public fun CustomButton(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  contentPaddingValues: PaddingValues = PaddingValues(0.dp),
  onClick: () -> Unit,
  content: @Composable BoxScope.() -> Unit = {},
) {
  val interactionSource = remember { MutableInteractionSource() }
  val pressed by interactionSource.collectIsPressedAsState()
  val scale by animateFloatAsState(if (pressed) 0.9F else 1F, label = "AnimateFloat")
  Box(
    modifier = Modifier
      .scale(scale)
      .then(modifier)
      .clickable(enabled = enabled, onClick = onClick)
      .padding(contentPaddingValues),
    contentAlignment = Alignment.Center,
    content = content,
  )
}
