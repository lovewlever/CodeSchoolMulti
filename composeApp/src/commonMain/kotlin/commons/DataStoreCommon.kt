package commons

import dataStoreGetData
import dataStorePutData


object DataStoreCommon {

    suspend inline fun <reified T> putData(key: String, value: T) {
        dataStorePutData(key, value)
    }

    suspend inline fun <reified T> getData(key: String, def: T): T {
        return dataStoreGetData(key, def)
    }
}