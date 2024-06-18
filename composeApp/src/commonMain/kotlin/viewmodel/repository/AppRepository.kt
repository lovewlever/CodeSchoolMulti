package viewmodel.repository

import viewmodel.data.ResultData


class AppRepository: Repository() {

    suspend fun queryAppInfo(): ResultData<List<String>>? {
        try {
            val str = requestGet<List<String>>("https://autumnfish.cn/api/joke/list?num=1")
            println(str)
            return str
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}