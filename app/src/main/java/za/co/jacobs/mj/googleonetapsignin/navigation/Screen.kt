package za.co.jacobs.mj.googleonetapsignin.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("Login")
    data object Profile : Screen("Profile")
}
