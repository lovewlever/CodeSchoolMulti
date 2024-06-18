package ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import codeschoolmulti.composeapp.generated.resources.Res
import codeschoolmulti.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LinkManager(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Link Manager", style = MaterialTheme.typography.h4)
        Spacer(Modifier.height(8.dp))
        FlowRow {
            for (i in 0 .. 20) {
                TextButton(onClick = {}) {
                    Text("Button_${i}", modifier = Modifier.align(Alignment.CenterVertically), style = MaterialTheme.typography.h5)
                    Image(painter = painterResource(Res.drawable.compose_multiplatform), contentDescription = "", modifier = Modifier.size(16.dp).align(Alignment.CenterVertically))
                }
            }
        }
    }
}