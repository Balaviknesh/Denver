package me.mavisii.common.service

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import me.mavisii.common.httpClient
import me.mavisii.common.initLogger
import me.mavisii.common.model.ResponseObject
import me.mavisii.common.model.ResponseObject.*


object KtorService {

    private val client = httpClient {

        //Header
        install(DefaultRequest) {
            header("Accept", "application/json")
            header("Content-type", "application/json")
            contentType(ContentType.Application.Json)
        }

        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
                encodeDefaults = true
                classDiscriminator = "#class"}
            serializer = KotlinxSerializer(json)
        }

        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        //Now you see response logs inside terminal
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }

        //Print other logs
        install(ResponseObserver) {
            onResponse { response ->
                Napier.d("HTTP status: ${response.status.value}", tag = "KTOR-SERVICE")
            }
        }

    }.also { initLogger() }

    fun getClient(): HttpClient {
        return client
    }
}