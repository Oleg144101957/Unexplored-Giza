package mycity.airpor.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mycity.airpor.game.GameViewModel


@Composable
fun NavigationFile() {

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
    }
}