package mycity.airpor.ui.theme

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mycity.airpor.game.GameViewModel


@Composable
fun NavigationFile(backPress : (w: WebView) -> Unit) {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = ScreenDestination.OneScreen.endpoint){

        composable(route = ScreenDestination.OneScreen.endpoint){
            OneScreen(navHostController)
        }

        composable(route = ScreenDestination.TwoScreen.endpoint){
            TwoScreen(navHostController)
        }

        composable(route = ScreenDestination.ThreeScreen.endpoint){
            ThreeScreen(navHostController)
        }

        composable(route = ScreenDestination.FourScreen.endpoint){
            //Rules
            FourScreen()
        }

        composable(route = ScreenDestination.FiveScreen.endpoint){
            //Game
            val viewModel = viewModel<GameViewModel>()
            FiveScreen(viewModel)
        }

        composable(route = ScreenDestination.SixScreen.endpoint){
            //Unexplored Giza
            SixScreen(navHostController)
        }

        composable(route = ScreenDestination.SevenScreen.endpoint){
            //Unexplored Giza
            SevenScreen(backPress)
        }
    }
}