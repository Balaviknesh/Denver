package ui.weather

import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TransitionAnimation() {
    val infiniteTransition = rememberInfiniteTransition()



    val rain1 by infiniteTransition.animateFloat(
        initialValue = -10F,
        targetValue = 100F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, delayMillis = 100,easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

}
