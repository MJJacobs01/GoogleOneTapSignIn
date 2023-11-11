package za.co.jacobs.mj.googleonetapsignin.presentation.screen.login

import android.annotation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*

/**
 * Created by MJ Jacobs on 2023/11/11 at 15:07
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController:NavHostController
) {
    Scaffold(
        topBar = {
            LoginTopBar()
        },
        content = {_->
            LoginContent(
                signInState = true,
                messageBarState = MessageBarState(),
                onButtonClicked = {

                }
            )
        }
    )
}