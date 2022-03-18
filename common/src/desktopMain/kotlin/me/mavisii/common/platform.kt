package me.mavisii.common

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import java.util.concurrent.TimeUnit
import io.ktor.client.engine.okhttp.*

actual fun getPlatformName(): String {
    return "Desktop"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)

    engine {
        config {
            retryOnConnectionFailure(false)
            connectTimeout(30, TimeUnit.SECONDS)
        }
    }
}

actual fun initLogger(){
    Napier.base(DebugAntilog())
}