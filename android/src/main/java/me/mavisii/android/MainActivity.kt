package me.mavisii.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import me.mavisii.common.getPlatformName
import me.mavisii.common.repository.RepositoryImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = RepositoryImpl()
        val vm = MainViewModel(repo)
        setContent {
            MaterialTheme {
                App(vm)
            }
        }
    }

    @Composable
    fun App(model: MainViewModel) {
        var text by remember { mutableStateOf("Hello, World!") }
        model.liveValue.observe(this) {
            println(it)
            if(it!=null)
                text = "Weather: $it"
        }
        Button(onClick = {
            model.getWeather()
        }) {
            Text(text)
        }
    }
}