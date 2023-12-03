package za.co.jacobs.mj.googleonetapsignin.presentation.screen.login

import android.annotation.*
import android.app.Activity
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.*
import androidx.hilt.navigation.compose.*
import androidx.navigation.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*
import za.co.jacobs.mj.googleonetapsignin.navigation.*
import za.co.jacobs.mj.googleonetapsignin.presentation.screen.common.*
import za.co.jacobs.mj.googleonetapsignin.util.*

/**
 * Created by MJ Jacobs on 2023/11/11 at 15:07
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse

    Scaffold(
        topBar = {
            LoginTopBar()
        },
        content = { _ ->
            LoginContent(
                signInState = signedInState,
                messageBarState = messageBarState,
                onButtonClicked = {
                    loginViewModel.saveSignedInState(signedIn = true)
                }
            )
        }
    )

    val activity = LocalContext.current as Activity
    StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            loginViewModel.verifyTokenOnBackend(request = ApiRequest(tokenId = tokenId))
        },
        onDialogDismissed = {
            loginViewModel.saveSignedInState(signedIn = false)
        },
        launcher = { activityLauncher ->
            if (signedInState) {
                signIn(
                    activity = activity,
                    launchActivityResult = { intentSenderRequest ->
                        activityLauncher.launch(intentSenderRequest)
                    },
                    accountNotFound = {
                        loginViewModel.saveSignedInState(signedIn = false)
                        loginViewModel.updateMessageBarState()
                    }
                )
            }
        }
    )

    LaunchedEffect(key1 = apiResponse) {
        when (apiResponse) {
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<ApiResponse>).data.isSuccess
                if (response) {
                    navigateToProfileScreen(navController)
                } else {
                    loginViewModel.saveSignedInState(signedIn = false)
                }
            }

            else -> {
                /** No-Op **/
            }
        }
    }
}

private fun navigateToProfileScreen(
    navController: NavHostController
) {
    navController.navigate(route = Screen.Profile.route) {
        popUpTo(route = Screen.Login.route) {
            inclusive = true
        }
    }
}