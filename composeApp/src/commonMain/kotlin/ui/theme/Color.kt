package ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFF42b983)
val Purple500 = Color(0xFF42b983)
val Purple700 = Color(0xFF3700B3)


val Colors.spacerLineColor: Color
    get() = Color(0xFFEBEAEA)

val Colors.textMainColor: Color
    @Composable get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val Colors.dividerColor: Color
    @Composable get() = if (isSystemInDarkTheme()) Color.White.copy(0.3F) else Color.Black.copy(0.3F)

val Colors.textHintColor: Color
    @Composable  get() = if (isSystemInDarkTheme()) Color.White.copy(0.5F) else Color.Black.copy(0.5F)