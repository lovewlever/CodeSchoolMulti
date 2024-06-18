package ui.settings

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import viewmodel.AppViewModel

private object NavSettingsGraph {
    val LinkManager = "LinkManager"
    val OtherManager = "OtherManager"
    val UserList = "UserList"
    val LabelManager = "LabelManager"
}

@Composable
fun SettingsPage(modifier: Modifier = Modifier,
                 appViewModel: AppViewModel = viewModel { AppViewModel() }
) {
    var linkManagerGraph by remember { mutableStateOf("") }
    Row(modifier = modifier.fillMaxSize().padding(top = 24.dp)) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            listOf(
                Triple("title", "USER SETTINGS", {}),
                Triple("menu" , "User Info", {}),
                Triple("line" , "", {}),
                Triple("title" , "SEARCH SETTING", {}),
                Triple("menu" , "Match Setting", {}),
                Triple("line" , "", {}),
                Triple("title" , "OTHERS SETTING", {}),
                Triple("menu" , "My Message", {}),
                Triple("menu" , "Private Code Block", {}),
                Triple("menu" , "Deleted Code Block", {}),
                Triple("menu" , "Link Manager", { linkManagerGraph = NavSettingsGraph.LinkManager }),
                Triple("menu" , "Label Manager", { linkManagerGraph =
                    NavSettingsGraph.LabelManager
                }),
                Triple("menu" , "Other Setting", { linkManagerGraph =
                    NavSettingsGraph.OtherManager
                }),
                Triple("line" , "", {}),
                Triple("title" , "ADMIN SETTING", {}),
                Triple("menu" , "User List", { linkManagerGraph = NavSettingsGraph.UserList }),
            ).forEachIndexed { index, pair ->
                if (pair.first == "title") {
                    Text(pair.second, style = MaterialTheme.typography.h6)
                } else if (pair.first == "menu") {
                    TextButton(onClick = {
                        pair.third()
                    }) {
                        Text(pair.second)
                    }
                } else if (pair.first == "line") {
                    Divider(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth().height(1.dp))
                }
            }
        }
        Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
        Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.TopCenter) {
            AnimatedContent(linkManagerGraph) {
                when(it) {
                    NavSettingsGraph.LinkManager -> LinkManager()
                    NavSettingsGraph.OtherManager -> OtherManager()
                    NavSettingsGraph.UserList -> UserList()
                    NavSettingsGraph.LabelManager -> LabelManager()
                }
            }
        }
    }
}