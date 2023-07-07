package mycity.airpor.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import mycity.airpor.R


@Composable
fun OneScreen(navHostController: NavHostController){


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
    }

}