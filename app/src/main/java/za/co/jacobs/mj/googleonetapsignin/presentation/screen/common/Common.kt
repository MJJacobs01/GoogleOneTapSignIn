package za.co.jacobs.mj.googleonetapsignin.presentation.screen.common

import android.app.Activity
import android.util.*
import androidx.activity.result.*
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import za.co.jacobs.mj.googleonetapsignin.util.*

/**
 * Created by MJ Jacobs on 2023/11/19 at 15:22
 */

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