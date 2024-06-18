package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import viewmodel.data.LabelData
import viewmodel.repository.LabelLinkRepository

class LabelLinkViewModel: ViewModel() {

    private val labelLinkRepository by lazy { LabelLinkRepository() }

    val labelDataStateList  = mutableStateListOf<LabelData>()

    // https://dfordog.cn/api/classify/query-classify-list
    fun queryClassifyList() {
        viewModelScope.launch {
            labelDataStateList.clear()
            labelLinkRepository.queryClassifyList().let {
                labelDataStateList.addAll(it.data!!)
            }
        }
    }
}