package ui.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LabelManager(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Label Manager", style = MaterialTheme.typography.h4)
        Spacer(Modifier.height(8.dp))
        for (i in 0..5) {
            Text("Label_${i}", style = MaterialTheme.typography.h4)
            Spacer(Modifier.height(4.dp))
            FlowRow(
                modifier = Modifier.width(500.dp).border(
                    width = 1.dp,
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(8.dp)
                )
            ) {
                for (i in 0..20) {
                    TextButton(onClick = {}) {
                        Text(
                            "Button_${i}",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}