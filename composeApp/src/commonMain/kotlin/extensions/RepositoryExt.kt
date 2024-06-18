package extensions

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import viewmodel.repository.Repository

private val _httpClient = HttpClient {
    this.install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            useAlternativeNames = true
        })
    }
    this.install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}

val Repository.httpclient: HttpClient
    get() = _httpClient