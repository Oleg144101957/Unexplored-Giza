package mycity.airpor.ui.theme

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mycity.airpor.R
import mycity.airpor.game.Constants
import mycity.airpor.game.GameViewModel


@Composable
fun FiveScreen(viewModel: GameViewModel){
    //Game play


    val state = viewModel.elementsList.collectAsState()

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.back_1080_1920),
            contentDescription = "main back",
            modifier = Modifier.fillMaxSize(),
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

            Box(modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.im_1),
                    contentDescription = "scores_back",
                    modifier = Modifier.align(Alignment.Center)
                )

                Text(
                    text = "Score 0",
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
                                        })
                                    }
                                )
                            }
                        }
                    }
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.im_2),
            contentDescription = "god2",
            modifier = Modifier
                .align(Alignment.BottomStart)
        )

    }
}