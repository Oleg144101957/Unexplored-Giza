package mycity.airpor.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import mycity.airpor.R
import mycity.airpor.game.Constants


@Composable
fun OneScreen(navHostController: NavHostController){

    val alpha = remember { Animatable(1f) }


    LaunchedEffect(Unit){
        while (true){
            alpha.animateTo(
                targetValue = 0.3f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 500,
                        easing = FastOutLinearInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }


    LaunchedEffect(Unit){
        delay(1500)
        navHostController.navigate(ScreenDestination.TwoScreen.endpoint)
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.back_1080_1920),
            contentDescription = "loading_back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "Loading...",
            fontFamily = Constants.lemonFont,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(alpha.value)
        )
    }

}