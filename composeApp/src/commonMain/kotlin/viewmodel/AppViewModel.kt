package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import commons.DataStoreCommon
import getPlatformCmd
import kotlinx.coroutines.launch
import viewmodel.repository.AppRepository

class AppViewModel: ViewModel() {

    private val appRepository by lazy { AppRepository() }
    var searchContent by mutableStateOf("")
    var todayWink by mutableStateOf("")

    fun queryAppInfo() {
        viewModelScope.launch {
            todayWink = appRepository.queryAppInfo()?.data?.get(0) ?: "NULL"
            DataStoreCommon.putData("key", "测试--Test--Test")
            todayWink += DataStoreCommon.getData("key", "Default")
        }
    }
}