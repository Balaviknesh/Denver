import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.Theme.Companion.agora
import ui.Theme.Companion.timberwolf
import ui.Theme.Companion.title_background
import ui.weather.TransitionAnimation
import ui.weather.WeatherScape

@Preview
@Composable
fun WeatherScreen() {

    Row {

        Column {
            TitleView("Weather")
            Box(modifier = Modifier.fillMaxWidth()){
                val weather = TodayWeather("32°C", "Sunny", "")
                WeatherScape()
                TodayWeatherUI(weather)
            }
        }

    }



}


data class TodayWeather(val temp: String, val description: String, val imageUrl: String)

@Composable
fun TodayWeatherUI(weather: TodayWeather) {
    Row(
        modifier = Modifier.offset(y= (-100).dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Column{
            TransitionAnimation()
            Text(weather.temp, color = agora, fontSize = 48.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(weather.description, color = agora, fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}


@Composable
fun TitleView(title: String) {
    Row(
        modifier = Modifier
            .background( brush = Brush.verticalGradient(
                colors = listOf(
                    timberwolf,
                    Color(116, 173, 245)
                )
            ))
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(title, color = agora, style = MaterialTheme.typography.h4)
    }
}