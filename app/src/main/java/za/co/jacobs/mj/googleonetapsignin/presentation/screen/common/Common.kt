package za.co.jacobs.mj.googleonetapsignin.presentation.screen.common

import android.app.Activity
import android.util.*
import androidx.activity.compose.*
import androidx.activity.result.*
import androidx.activity.result.contract.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.*
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import za.co.jacobs.mj.googleonetapsignin.util.*

/**
 * Created by MJ Jacobs on 2023/11/19 at 15:22
 */

@Composable
fun StartActivityForResult(
    key: Any,
    onResultReceived: (tokenId: String) -> Unit,
    onDialogDismissed: () -> Unit,
    launcher: (ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) -> Unit
) {
    val activity = LocalContext.current as Activity
    val activityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { activityResult ->
            try {
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    val oneTapClient = Identity.getSignInClient(activity)
                    val credentials = oneTapClient.getSignInCredentialFromIntent(activityResult.data)
                    val tokenId: String? = credentials.googleIdToken
                    if (tokenId != null) {
                        onResultReceived(tokenId)
                    } else {
                        Log.d("StartActivityForResult", "Black scrim clicked, dialog clicked")
                        onDialogDismissed()
                    }
                }
            } catch (e: ApiException) {
                when (e.statusCode) {
                    CommonStatusCodes.CANCELED -> {
                        Log.d("StartActivityForResult", "OneTap dialog cancelled")
                        onDialogDismissed()
                    }

                    CommonStatusCodes.NETWORK_ERROR -> {
                        Log.d("StartActivityForResult", "OneTap Network error")
                        onDialogDismissed()
                    }

                    else -> {
                        Log.d("StartActivityForResult", "${e.message}")
                        onDialogDismissed()
                    }
                }
            }
        }
    )

    LaunchedEffect(
        key1 = key,
        block = {
            launcher(activityLauncher)
        }
    )
}

fun signIn(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit
) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(Constants.clientId)
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()
    oneTapClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            try {
                launchActivityResult(
                    IntentSenderRequest.Builder(
                        result.pendingIntent.intentSender
                    ).build()
                )
            } catch (e: Exception) {
                Log.d("SignIn", "Couldn't start One Tap UI ${e.message}")
            }
        }.addOnFailureListener {
            Log.d("SignIn", "Signing Up...")
            signUp(
                activity = activity,
                launchActivityResult = launchActivityResult,
                accountNotFound = accountNotFound
            )
        }
}

fun signUp(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit
) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(Constants.clientId)
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()
    oneTapClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            try {
                launchActivityResult(
                    IntentSenderRequest.Builder(
                        result.pendingIntent.intentSender
                    ).build()
                )
            } catch (e: Exception) {
                Log.d("SignIn", "Couldn't start One Tap UI ${e.message}")
            }
        }.addOnFailureListener {
            Log.d("SignIn", "Signing Up...")
            accountNotFound()
        }
}