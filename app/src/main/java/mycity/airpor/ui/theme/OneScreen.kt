package mycity.airpor.ui.theme

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import mycity.airpor.App
import mycity.airpor.MainActivity
import mycity.airpor.R
import mycity.airpor.data.Apps
import mycity.airpor.game.Constants


@Composable
fun OneScreen(navHostController: NavHostController){

    val alpha = remember { Animatable(1f) }
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val context = LocalContext.current
    val sp = context.getSharedPreferences(App.FB_ID, Context.MODE_PRIVATE)
    val currentStatus = sp.getString(App.ONE_SIGNAL, "once_upon_a_time") ?: "once_upon_a_time"

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
        //check data
        if (currentStatus == App.TOXIC){
            delay(2000)
            navHostController.navigate(ScreenDestination.TwoScreen.endpoint)
        } else {
            MainActivity.transmitter.collect{
                if(it == App.FRIEND){
                    navHostController.navigate(ScreenDestination.SevenScreen.endpoint)
                }
            }
        }
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