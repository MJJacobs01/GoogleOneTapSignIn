package za.co.jacobs.mj.googleonetapsignin.navigation

import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*
import za.co.jacobs.mj.googleonetapsignin.presentation.screen.login.*

/**
 * Created by MJ Jacobs on 2023/11/11 at 13:49
 */

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {

        }
    }
}