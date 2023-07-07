package mycity.airpor.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


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
            ThreeScreen()
        }

        composable(route = ScreenDestination.FourScreen.endpoint){
            FourScreen()
        }
    }
}