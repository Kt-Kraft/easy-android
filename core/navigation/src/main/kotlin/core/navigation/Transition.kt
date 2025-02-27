package core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

public val enterPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
  {
    fadeIn(
      animationSpec = tween(
        200, easing = LinearEasing,
      ),
    ) + slideIntoContainer(
      animationSpec = tween(200, easing = LinearEasing),
      towards = AnimatedContentTransitionScope.SlideDirection.Start,
      initialOffset = { it },
    )
  }

public val exitPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
  fadeOut(
    animationSpec = tween(
      300, easing = LinearEasing,
    ),
  ) + slideOutOfContainer(
    animationSpec = tween(300, easing = LinearEasing),
    towards = AnimatedContentTransitionScope.SlideDirection.End,
    targetOffset = { -it / 4 },
  )
}

public val enterPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
  {
    fadeIn(
      animationSpec = tween(
        300, easing = LinearEasing,
      ),
    ) + slideIntoContainer(
      animationSpec = tween(300, easing = LinearEasing),
      towards = AnimatedContentTransitionScope.SlideDirection.Start,
      initialOffset = { -it / 4 },
    )
  }

public val exitPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
  fadeOut(
    animationSpec = tween(
      200, easing = LinearEasing,
    ),
  ) + slideOutOfContainer(
    animationSpec = tween(200, easing = LinearEasing),
    towards = AnimatedContentTransitionScope.SlideDirection.End,
    targetOffset = { it },
  )
}
