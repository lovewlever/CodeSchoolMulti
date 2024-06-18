package ui.windows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.datetime.Clock
import viewmodel.WindowsAdbViewModel
import viewmodel.data.WindowsIPData

class IPManagerState {
    var show by mutableStateOf(false)

    fun showNow() {
        this.show = true
    }

    fun hide() {
        this.show = false
    }
}

@Composable
fun rememberIPManagerState() = remember {
    IPManagerState()
}

@Composable
fun WindowsIPManagerDialog(
    modifier: Modifier = Modifier,
    ipManagerState: IPManagerState,
    windowsAdbViewModel: WindowsAdbViewModel
) {
    if (ipManagerState.show) {
        var id by remember { mutableStateOf("") }
        var ip by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        LaunchedEffect(true) {
            windowsAdbViewModel.queryIpList()
        }

        Dialog(onDismissRequest = { ipManagerState.hide() }) {
            Surface(modifier = modifier, shape = RoundedCornerShape(12.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    windowsAdbViewModel.ipStateList.forEach {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "${it.name}: ${it.ip}")
                            Spacer(Modifier.weight(1f))
                            OutlinedButton(onClick = {
                                windowsAdbViewModel.removeIpToManager(it)
                            }, colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)) {
                                Text(text = "删除")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            OutlinedButton(onClick = {
                                id = it.id
                                ip = it.ip
                                name = it.name
                            }, colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)) {
                                Text(text = "修改")
                            }
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(modifier = Modifier.weight(1f), value = ip, onValueChange = { ip = it }, label = {
                            Text(text = "IP")
                        })
                        Spacer(Modifier.width(8.dp))
                        OutlinedTextField(modifier = Modifier.weight(1f), value = name, onValueChange = { name = it }, label = {
                            Text(text = "NAME")
                        })
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = {
                            if (ip.isBlank()) {

                            } else if (name.isBlank()) {

                            } else {
                                val ids = id.ifBlank { "${Clock.System.now().toEpochMilliseconds()}" }
                                windowsAdbViewModel.addIpToManager(WindowsIPData(ids, ip, name))
                                id = ""
                                ip = ""
                                name = ""
                            }
                        }) {
                            Text(text = "添加/修改")
                        }
                    }
                }
            }

        }
    }
}