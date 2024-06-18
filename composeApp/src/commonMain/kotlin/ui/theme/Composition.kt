package ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSignInDialogShowState = staticCompositionLocalOf { mutableStateOf(false) }