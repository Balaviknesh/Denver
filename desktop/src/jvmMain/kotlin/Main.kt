import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application



fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            App()
        }
    }
}

@Composable
fun App() {

    var text by remember { mutableStateOf("Hello, World!") }


    Button(onClick = {
        text = "Hello Pi"
    }
    ) {
        Text(text)
    }

}

