package me.mavisii.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ResponseObject{

    @Serializable
    open class MetaDataResponse(@SerialName("metadata")var metadata: Metadata = Metadata(0, "")): ResponseObject()

    @Serializable
    data class Metadata(val statusCode: Int, val statusMessage: String, val tags: Tags? = null)

    @Serializable
    data class Tags(val errorCode: Int =0, val errorMessages: String = "")

    @Serializable
    data class WeatherResponse(
        @SerialName("data")
        val weather: Weather
    ): MetaDataResponse()

    @Serializable
    data class Weather(
        @SerialName("location")
        val location: WeatherLocation,
        @SerialName("current")
        val currentWeather: WeatherCurrent
    )

    @Serializable
    data class WeatherCondition(
        var text: String? = "",
        var icon: String? = "",
        var code: Int
    )

    @Serializable
    data class WeatherCurrent(
        var last_updated_epoch: Long,
        var last_updated: String? = "",
        var temp_c: Double,
        var temp_f: Double,
        var is_day: Int? = null,
        var condition: WeatherCondition?,
        var wind_mph: Double,
        var wind_kph: Double,
        var wind_degree: Int,
        var wind_dir: String? = "",
        var pressure_mb: Double,
        var pressure_in: Double,
        var precip_mm: Double,
        var precip_in: Double,
        var humidity: Double,
        var cloud: Int,
        var feelslike_c: Double,
        var feelslike_f: Double,
        var vis_km: Double,
        var vis_miles: Double,
        var uv: Double,
        var gust_mph: Double,
        var gust_kph: Double,
    )

    @Serializable
    data class WeatherLocation(
        var name: String? = "",
        var region: String? = "",
        var country: String? = "",
        var lat: Double,
        var lon: Double,
        var tz_id: String? = "",
        var localtime_epoch: Long,
        var localtime: String? = ""
    )

}