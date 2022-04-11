package ui.weather

import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window

@Preview
@Composable
fun WeatherScape() {


    val infiniteTransition = rememberInfiniteTransition()
    val cloudMove by infiniteTransition.animateFloat(
        initialValue = -50F,
        targetValue = 900F,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, delayMillis = 1000,easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val cloudMove2 by infiniteTransition.animateFloat(
        initialValue = -100F,
        targetValue = 850F,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, delayMillis = 2000,easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(
                Color(116, 173, 245)
            ), verticalAlignment = Alignment.Top
        ) {

            Box() {

                Image(
                    modifier = Modifier.fillMaxWidth().size(75.dp, 75.dp),
                    alignment = Alignment.Center,
                    painter = painterResource("weather/Solar/Sun.png"),
                    contentDescription = "Sun"
                )
                Image(
                    painter = painterResource("weather/Cloud/Day/Cloud.png"),
                    contentDescription = "Cloud 1",
                    modifier = Modifier.offset(x = cloudMove.dp).size(75.dp, 75.dp)
                )


                Image(
                    painter = painterResource("weather/Cloud/Day/Cloud2.png"),
                    contentDescription = "Cloud 2",
                    modifier = Modifier.offset(x= cloudMove2.dp, y = (-30).dp).size(75.dp, 75.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(116, 173, 245),
                            Color(30, 193, 246),
                            Color(153, 223, 104)
                        )
                    )
                ), verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center

        ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource("weather/Landscapes/Summer.png"),
                    contentDescription = "Sun"
                )
        }

    }


}