package mycity.airpor.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mycity.airpor.R


@Composable
fun TwoScreen(navHostController: NavHostController) {

    //Launcher

    Box(modifier = Modifier.fillMaxSize()){
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
    }
}