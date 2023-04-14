import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.domain.navigation.Screen
import com.example.camerascanner.scannerUI.scanScreen.ScannerScreen
import com.example.handla.ui.HandlaScreen

@Composable
fun NavigationSystem() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Handla.route,
        builder = {
            addHandlaScreen(navController = navController)

            addScannerScreen(navController = navController)
        }
    )

}


fun NavGraphBuilder.addHandlaScreen(
    navController: NavController,
) {
    composable(
        route = Screen.Handla.route,
    ){
        HandlaScreen(navController)
    }
}

fun NavGraphBuilder.addScannerScreen(navController: NavController) {
    composable(
        route = Screen.Scanner.route
    ){
        ScannerScreen(navController)
    }
}



