package viewmodel.repository

import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.serialization.builtins.ListSerializer
import viewmodel.data.LabelData
import viewmodel.data.ResultData

class LabelLinkRepository: Repository() {

    suspend fun queryClassifyList(): ResultData<List<LabelData>> {
        val result = requestPOST<List<LabelData>>("api/classify/query-classify-list")
        println(result)
        return result
    }
}