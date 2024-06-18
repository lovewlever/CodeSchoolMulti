package ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset

/**
 * 指定当您navigate()到达此目的地时运行的动画。
 */
fun enterTransition(): EnterTransition {
    return slideInHorizontally { w -> w }
}

/**
 * 指定此目的地在经过 . 后重新进入屏幕时运行的动画popBackStack()。这默认为enterTransition
 */
fun popEnterTransition(): EnterTransition? {
    return slideIn(animationSpec = spring(stiffness = 1000F), initialOffset = {
        IntOffset(x = -50, y = 0)
    }) + fadeIn(animationSpec = tween(50))
}

/**
 * 指定当您通过导航到另一个目的地离开此目的地时运行的动画。
 */
fun exitTransition(): ExitTransition {
    return slideOutHorizontally { w -> -w }
}

/**
 * 指定在您将其从返回堆栈中弹出后，当此目标离开屏幕时运行的动画。这默认为exitTransition.
 */
fun popExitTransition(): ExitTransition? {
    return slideOut(animationSpec = spring(stiffness = 1000F), targetOffset = {
        IntOffset(x = 150, y = 0)
    }) + fadeOut(animationSpec = tween(100))
}