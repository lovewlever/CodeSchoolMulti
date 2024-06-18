package ui.windows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import viewmodel.WindowsAdbViewModel

@Composable
fun WindowsAdbHelp(modifier: Modifier = Modifier, winAdbViewMode: WindowsAdbViewModel = viewModel { WindowsAdbViewModel() }) {

    val ipManagerState = rememberIPManagerState()

    LaunchedEffect(true) {
        winAdbViewMode.queryIpList()
    }

    Column(modifier = modifier.fillMaxSize().padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Text("Only support windows platform")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("IP: ")
            OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                onClick = { winAdbViewMode.dropdownExp = true }) {
                Text(if (winAdbViewMode.selectedWinIPData != null) "${winAdbViewMode.selectedWinIPData?.name}:${winAdbViewMode.selectedWinIPData?.ip}" else "Select")
                DropdownMenu(expanded = winAdbViewMode.dropdownExp, onDismissRequest = { winAdbViewMode.dropdownExp = false }) {
                    winAdbViewMode.ipStateList.forEach {
                        DropdownMenuItem(onClick = {
                            winAdbViewMode.selectedWinIPData = it
                            winAdbViewMode.dropdownExp = false
                        }) {
                            Text(it.name)
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1F))
            Column {
                OutlinedButton(
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { winAdbViewMode.connectAdb() }) { Text("连接") }
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { winAdbViewMode.disConnectAdb() }) { Text("断开") }
            }
            Spacer(Modifier.width(14.dp))
            Column {
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { winAdbViewMode.reStartAdb() }) { Text("重启ADB") }
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { winAdbViewMode.reStartDevice() }) { Text("重启设备") }
            }
            Spacer(Modifier.width(14.dp))
            Column {
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { winAdbViewMode.devices() }) { Text("列表") }
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { ipManagerState.showNow() }) { Text("设备管理") }
            }
            Spacer(Modifier.width(14.dp))
            Column {
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { winAdbViewMode.startScrcpy() }) { Text("Scrcpy") }
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    onClick = { winAdbViewMode.closeJdks() }) { Text("CloseJDK") }
            }
        }

        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(winAdbViewMode.cmdPrintList) {
                Text(it)
            }
        }
    }

    WindowsIPManagerDialog(ipManagerState = ipManagerState, windowsAdbViewModel = winAdbViewMode)
}