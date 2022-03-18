package me.mavisii.common.service

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse


class ApiService(private val client: HttpClient, private val baseUrl: String) {

    suspend fun getCurrentWeather(locationName: String): HttpResponse {
        return client.get("${baseUrl}colorado-service/weather/current?locationName=${locationName}")
    }
}