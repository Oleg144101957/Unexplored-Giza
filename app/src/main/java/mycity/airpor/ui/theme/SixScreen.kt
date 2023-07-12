package mycity.airpor.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mycity.airpor.R
import mycity.airpor.game.Constants


@Composable
fun SixScreen(navigation: NavHostController){


    val infiniteTransition = rememberInfiniteTransition()
    val angle = infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Box(modifier = Modifier.fillMaxSize()) {
        

        
        Image(
            painter = painterResource(id = R.drawable.back_1080_1920),
            contentDescription = "back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        
        Column(modifier = Modifier.fillMaxWidth()) {

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp))
            
            Image(
                painter = painterResource(id = R.drawable.im_9),
                contentDescription = "rotation element",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .graphicsLayer(rotationZ = angle.value)
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp))
            

            Text(
                text = stringResource(id = R.string.secret),
                color = Color.White,
                fontFamily = Constants.lemonFont,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp))

            
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Arrow back",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navigation.navigate(ScreenDestination.TwoScreen.endpoint)
                    }
            )
        }
    }
    
}