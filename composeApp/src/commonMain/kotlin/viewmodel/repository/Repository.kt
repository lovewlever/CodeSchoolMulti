package viewmodel.repository

import commons.Constants
import extensions.httpclient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import viewmodel.data.ResultData

abstract class Repository {

    suspend inline fun <reified T>requestGet(url: String): ResultData<T> {
        return try {
            httpclient.get(url).body<ResultData<T>>()
        } catch (e: Exception) {
            ResultData()
        }
    }

    suspend inline fun <reified T>requestPOST(path: String): ResultData<T> {
        return try {
            httpclient.post {
                headers {
                    this["Token"] = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJCRkIxOTdFNDgyRjg0ODQ3MkY2N0MwODcyQUNFRUIzQyIsInVzZXJOYW1lIjoiZ3EiLCJ1c2VyQWdlbnQiOiIxMjM0NTYiLCJleHAiOjE3MTgwOTQ0MTAsIm5iZiI6MTcxNzQ4OTYxMH0.GlJ7HCgA3mLo3su4NJsR4ligtsHZcvURyCFyb4f0_hc"
                }
                url("${Constants.HostName}${path}")
            }.body<ResultData<T>>()
        } catch (e: Exception) {
            ResultData()
        }
    }

}