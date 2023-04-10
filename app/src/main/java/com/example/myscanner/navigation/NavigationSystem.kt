import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.example.camerascanner.scannerUI.ScannerScreen
import com.example.handla.ui.HandlaScreen
import com.example.myscanner.navigation.Screen

@Composable
fun NavigationSystem() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Handla.route,
        builder = {
            addHandlaScreen(
                navController = navController,
//                imageLoader = imageLoader
            )

            addScannerScreen()
        }
    )

}


fun NavGraphBuilder.addHandlaScreen(
    navController: NavController,
//    imageLoader: ImageLoader,
) {
    composable(
        route = Screen.Handla.route,
    ){
//        val viewModel: HeroListViewModel = hiltViewModel()
        HandlaScreen(
//            state = viewModel.state.value,
//            imageLoader = imageLoader,
//            navigateToDetailScreen = { heroId ->
//                navController.navigate("${Screen.HeroDetail.route}/$heroId")
//            }
        ){

//            navController.currentBackStackEntry?.savedStateHandle
//                ?.apply {
//                set(USER_ID_KEY, it)
//            }


            navController.navigate(Screen.Scanner.route)
        }
    }
}

fun NavGraphBuilder.addScannerScreen() {
    composable(
        route = Screen.Scanner.route
    ){
        ScannerScreen()
    }
}



