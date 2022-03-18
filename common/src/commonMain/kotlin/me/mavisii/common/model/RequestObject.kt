package me.mavisii.common.model

sealed class RequestObject {

    data class WeatherRequest(
        val locationName: String,
        val metric: WeatherMetricType,
        val foreCast: Boolean
    ) : RequestObject()

}