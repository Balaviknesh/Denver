import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application



fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Weather", state = WindowState(height = 480.dp, width = 800.dp)) {
        MaterialTheme {
            App()
        }
    }
}

@Preview
@Composable
fun App() {

    var text by remember { mutableStateOf("Hello, World!") }
    WeatherScreen()
}



