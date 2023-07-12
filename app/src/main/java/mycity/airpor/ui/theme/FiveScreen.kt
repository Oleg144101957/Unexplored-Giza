package mycity.airpor.ui.theme

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import mycity.airpor.R
import mycity.airpor.game.Constants
import mycity.airpor.game.GameViewModel


@Composable
fun FiveScreen(viewModel: GameViewModel){
    //Game play

    val context = LocalContext.current

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT



    val sharedPref = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
    val sharedName = sharedPref.getString(Constants.SHARED_NAME, "Lucky User") ?: "Lucky user"
    val userName = remember {
        mutableStateOf(sharedName)
    }

    val state = viewModel.elementsList.collectAsState()
    val win = viewModel.win.collectAsState()
    val alpha = remember { Animatable(1f) }
    val time = remember {
        mutableStateOf(59)
    }

    LaunchedEffect(key1 = "timer"){
        while (time.value>0){
            delay(1000)
            time.value -= 1
        }
    }



    LaunchedEffect(Unit){
        while (true){
            alpha.animateTo(
                targetValue = 0.35f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1500,
                        easing = FastOutLinearInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.back_1080_1920),
            contentDescription = "main back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )



        Image(
            painter = painterResource(id = R.drawable.im_3),
            contentDescription = "god",
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
        ) {

            Text(
                text = userName.value,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Box(modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.im_1),
                    contentDescription = "scores_back",
                    modifier = Modifier
                        .align(Alignment.Center)
                )

                Text(
                    text = "Time remains ${time.value}",
                    color = Color.White,
                    fontFamily = Constants.lemonFont,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ){
                Image(
                    painter = painterResource(id = R.drawable.im_4),
                    contentDescription = "game back",
                    modifier = Modifier
                        .size(720.dp)
                )


                if (time.value == 0){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        modifier = Modifier
                            .fillMaxWidth(0.60f)
                            .align(Alignment.Center)
                            .offset(y = 32.dp),
                        content = {
                            items(40){ i ->
                                Box(modifier = Modifier.padding(4.dp)){
                                    Image(
                                        painter = painterResource(id = state.value[i].image),
                                        contentDescription = "element $i",
                                        modifier = Modifier.alpha(alpha.value)
                                    )
                                }
                            }
                        }
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        modifier = Modifier
                            .fillMaxWidth(0.60f)
                            .align(Alignment.Center)
                            .offset(y = 32.dp),
                        content = {
                            items(40){ i ->
                                Box(modifier = Modifier.padding(4.dp)){
                                    Image(
                                        painter = painterResource(id = state.value[i].image),
                                        contentDescription = "element $i",
                                        modifier = Modifier.pointerInput(Unit){
                                            detectDragGestures(onDrag = { _, dragAmount ->
                                                if (dragAmount.x > 20){
                                                    viewModel.swapElementRight(i)
                                                } else if (dragAmount.x < -20){
                                                    viewModel.swapElementLeft(i)
                                                } else if (dragAmount.y > 20){
                                                    viewModel.swapElementDown(i)
                                                } else if (dragAmount.y < -20){
                                                    viewModel.swapElementUp(i)
                                                }
                                            }
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    )
                }

            }
        }

        Image(
            painter = painterResource(id = R.drawable.im_2),
            contentDescription = "god2",
            modifier = Modifier
                .align(Alignment.BottomStart)
        )

        if (time.value == 0){
            Text(
                text = "The time is over",
                fontFamily = Constants.lemonFont,
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .rotate(-45f)
            )
        }

        if (win.value){
            Text(
                text = "You win, go to the next level",
                fontSize = 16.sp,
                fontFamily = Constants.lemonFont,
                color = Color.Yellow,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp)
                    .background(Color.DarkGray)
                    .clickable {
                        time.value = 30
                        viewModel.recoverList()

                    }
            )
        }
    }
}