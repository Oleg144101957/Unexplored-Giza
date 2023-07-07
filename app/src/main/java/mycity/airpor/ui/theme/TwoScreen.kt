package mycity.airpor.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mycity.airpor.R

@Composable
fun TwoScreen(navHostController: NavHostController) {

    //Launcher

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.back_1080_1920),
            contentDescription = "launcher_back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        
        Image(
            painter = painterResource(id = R.drawable.element_11),
            contentDescription = "light",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .size(650.dp),
            contentScale = ContentScale.FillHeight
        )

        Image(
            painter = painterResource(id = R.drawable.element_12),
            contentDescription = "god",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(650.dp)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(bottom = 48.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.element_15),
                    contentDescription = "btn_background",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(ScreenDestination.FiveScreen.endpoint)
                        }
                )
                
                Text(
                    text = "Play Game",
                    fontSize = 32.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.element_15),
                    contentDescription = "btn_background",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(ScreenDestination.ThreeScreen.endpoint)
                        }
                )

                Text(
                    text = "Settings",
                    fontSize = 32.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.element_15),
                    contentDescription = "btn_background",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(ScreenDestination.FourScreen.endpoint)
                        }
                )

                Text(
                    text = "Rules",
                    fontSize = 32.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}