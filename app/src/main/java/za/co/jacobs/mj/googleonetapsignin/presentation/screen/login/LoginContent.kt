package za.co.jacobs.mj.googleonetapsignin.presentation.screen.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import za.co.jacobs.mj.googleonetapsignin.R
import za.co.jacobs.mj.googleonetapsignin.component.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*

/**
 * Created by MJ Jacobs on 2023/11/11 at 15:47
 */

@Composable
fun LoginContent(
    signInState: Boolean,
    messageBarState: MessageBarState,
    onButtonClicked: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            MessageBar(state = messageBarState)
        }

        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CentralContent(
                signInState = signInState,
                onButtonClicked = onButtonClicked
            )
        }
    }
}

@Composable
fun CentralContent(
    signInState: Boolean,
    onButtonClicked: () -> Unit
) {

    Image(
        painter = painterResource(id = R.drawable.google_g_logo),
        contentDescription = "Google G Logo",
        modifier = Modifier
            .padding(20.dp)
            .size(120.dp)
    )

    Text(
        text = "Sign in to continue",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

    Text(
        text = "If you want to access this app, \nyou have to first sign in",
        modifier = Modifier
            .alpha(0.5f)
            .padding(bottom = 40.dp, top = 4.dp),
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )

    GoogleButton(
        loadingState = signInState,
        onClick = onButtonClicked
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewLoginContent() {
    LoginContent(signInState = false, messageBarState = MessageBarState()) {

    }
}