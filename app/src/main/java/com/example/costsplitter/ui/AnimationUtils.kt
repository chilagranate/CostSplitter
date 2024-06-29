package com.example.costsplitter.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset

private const val ANIMATION_DURATION = 700

fun slideFromLeftAnimation(animationSpec: FiniteAnimationSpec<IntOffset> = tween(ANIMATION_DURATION)): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -it }, // Slide from left
        animationSpec = animationSpec
    )
}
fun slideFromRightAnimation(animationSpec: FiniteAnimationSpec<IntOffset> = tween(ANIMATION_DURATION)): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { it }, // Slide from right
        animationSpec = animationSpec
    )
}

fun slideToLeftAnimation(animationSpec: FiniteAnimationSpec<IntOffset> = tween(ANIMATION_DURATION)): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { it }, // slide to left
        animationSpec = animationSpec
    )
}

fun slideToRightAnimation(animationSpec: FiniteAnimationSpec<IntOffset> = tween(ANIMATION_DURATION)): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -it }, // slide to right
        animationSpec = animationSpec
    )
}