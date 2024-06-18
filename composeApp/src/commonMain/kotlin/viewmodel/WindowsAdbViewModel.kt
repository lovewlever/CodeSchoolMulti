package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commons.DataStoreCommon
import getPlatformCmd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viewmodel.data.WindowsIPData


class WindowsAdbViewModel: ViewModel() {

    private val IpListKey = "IpListKey"
    val ipStateList = mutableStateListOf<WindowsIPData>()
    val cmdPrintList = mutableStateListOf<String>()
    var dropdownExp by mutableStateOf(false)
    var selectedWinIPData by mutableStateOf<WindowsIPData?>(null)
    private val platformCmd = getPlatformCmd()
    private val ipRegex = Regex.fromLiteral( "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])(?::(?:[0-9]|[1-9][0-9]{1,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5]))?$")


    fun queryIpList() {
        viewModelScope.launch {
            ipStateList.clear()
            val list = DataStoreCommon.getData(IpListKey, mutableListOf<WindowsIPData>())
            ipStateList.addAll(list)
        }
    }

    fun addIpToManager(wipData: WindowsIPData) {
        viewModelScope.launch {
            val index = ipStateList.indexOfFirst { it.id == wipData.id }
            if (index != -1) {
                ipStateList.set(index, wipData)
            } else {
                ipStateList.add(wipData)
            }
            DataStoreCommon.putData(IpListKey, ipStateList)
            queryIpList()
        }
    }

    fun removeIpToManager(wipData: WindowsIPData) {
        viewModelScope.launch {
            ipStateList.remove(wipData)
            DataStoreCommon.putData(IpListKey, ipStateList)
            queryIpList()
        }
    }


    fun connectAdb() {
        cmdPrintList.clear()
        viewModelScope.launch(Dispatchers.Default) {
            selectedWinIPData?.let {
                cmdPrintList.add("正在连接: ${it.name}")
                val result = platformCmd.runCmd("adb connect ${it.ip}")
                cmdPrintList.add(result)
            }
        }
    }

    fun disConnectAdb() {
        cmdPrintList.clear()
        viewModelScope.launch(Dispatchers.Default) {
            selectedWinIPData?.let {
                cmdPrintList.add("断开 ${it.name} 连接")
                val result = platformCmd.runCmd("adb disconnect ${it.ip}")
                cmdPrintList.add(result)
            }
        }
    }

    fun reStartAdb() {
        cmdPrintList.clear()
        viewModelScope.launch(Dispatchers.Default) {
            cmdPrintList.add("正在重启ADB...")
            val result = platformCmd.runCmd("adb kill-server")
            cmdPrintList.add(result)
            val result1 = platformCmd.runCmd("adb start-server")
            cmdPrintList.add(result1)

        }
    }

    fun reStartDevice() {
        cmdPrintList.clear()
        viewModelScope.launch(Dispatchers.Default) {
            selectedWinIPData?.let {
                cmdPrintList.add("正在重启设备: ${it.name}")
                val result = platformCmd.runCmd("adb -s ${it.ip} reboot")
                cmdPrintList.add(result)
            }
        }
    }

    fun devices() {
        cmdPrintList.clear()
        viewModelScope.launch(Dispatchers.Default) {
            cmdPrintList.add("设备列表: ")
            val result = platformCmd.runCmd("adb devices")
            cmdPrintList.add(result)
        }
    }

    fun startScrcpy() {
        selectedWinIPData?.let {
            cmdPrintList.clear()
            cmdPrintList.add("正在运行Scrcpy: ${it.name}...")
            viewModelScope.launch(Dispatchers.Default) {
                val result = if (ipRegex.matches(it.ip)) {
                    platformCmd.runCmd("D:\\Program Files\\scrcpy\\scrcpy.exe --tcpip=${it.ip}")
                } else {
                    platformCmd.runCmd("D:\\Program Files\\scrcpy\\scrcpy.exe -s ${it.ip}")
                }
                cmdPrintList.add(result)
            }
        }
    }

    fun closeJdks() {
        cmdPrintList.clear()
        viewModelScope.launch {
            cmdPrintList.add("关闭OpenJdk进程")
            platformCmd.closeJdkExe()
        }
    }

}