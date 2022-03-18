package me.mavisii.common

import io.ktor.client.*

expect fun getPlatformName(): String

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

expect fun initLogger()