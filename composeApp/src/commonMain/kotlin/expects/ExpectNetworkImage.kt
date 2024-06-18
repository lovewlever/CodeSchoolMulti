package expects

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun ExpectNetworkImage(modifier: Modifier = Modifier, url: String)