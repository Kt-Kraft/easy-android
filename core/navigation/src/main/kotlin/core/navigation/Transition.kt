package core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

/**
 * Defines the enter transition for a push operation in navigation.
 * This transition consists of a fade-in effect and a slide-into-container effect.
 *
 * The fade-in effect is defined with a linear easing and a duration of 200 milliseconds.
 *
 * The slide-into-container effect is also defined with a linear easing and a duration of 200 milliseconds.
 * The direction of the slide is from the start.
 * The initial offset for the slide is equal to the size of the container.
 *
 * @return EnterTransition The composed enter transition for a push operation.
 */
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

/**
 * Defines the exit transition for a push operation in navigation.
 * This transition consists of a fade-out effect and a slide-out-of-container effect.
 *
 * The fade-out effect is defined with a linear easing and a duration of 300 milliseconds.
 *
 * The slide-out-of-container effect is also defined with a linear easing and a duration of 300 milliseconds.
 * The direction of the slide is towards the end.
 * The target offset for the slide is equal to negative one-fourth of the size of the container.
 *
 * @return ExitTransition The composed exit transition for a push operation.
 */
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

/**
 * Defines the enter transition for a pop operation in navigation.
 * This transition consists of a fade-in effect and a slide-into-container effect.
 *
 * The fade-in effect is defined with a linear easing and a duration of 300 milliseconds.
 *
 * The slide-into-container effect is also defined with a linear easing and a duration of 300 milliseconds.
 * The direction of the slide is from the start.
 * The initial offset for the slide is equal to negative one-fourth of the size of the container.
 *
 * @return EnterTransition The composed enter transition for a pop operation.
 */
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

/**
 * Defines the exit transition for a pop operation in navigation.
 * This transition consists of a fade-out effect and a slide-out-of-container effect.
 *
 * The fade-out effect is defined with a linear easing and a duration of 200 milliseconds.
 *
 * The slide-out-of-container effect is also defined with a linear easing and a duration of 200 milliseconds.
 * The direction of the slide is towards the end.
 * The target offset for the slide is equal to the size of the container.
 *
 * @return ExitTransition The composed exit transition for a pop operation.
 */
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
