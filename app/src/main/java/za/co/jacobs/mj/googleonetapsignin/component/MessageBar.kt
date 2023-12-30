package za.co.jacobs.mj.googleonetapsignin.component

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*
import za.co.jacobs.mj.googleonetapsignin.ui.theme.*
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Created by MJ Jacobs on 2023/11/11 at 14:40
 */

@Composable
fun MessageBar(
    state: MessageBarState
) {
    var startAnimation by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = state) {
        if (state.error != null) {
            errorMessage = when (state.error) {
                is SocketTimeoutException -> {
                    "Connection Timeout Exception"
                }

                is ConnectException -> {
                    "Internet connection unavailable"
                }

                else -> {
                    "${state.error.message}"
                }
            }
        }
        startAnimation = true
        delay(3000L)
        startAnimation = false
    }

    AnimatedVisibility(
        visible = state.error != null && startAnimation || state.message != null && startAnimation,
        enter = expandVertically(
            animationSpec = tween(300),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(300),
            shrinkTowards = Alignment.Top
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = if (state.error != null) ErrorRed else InfoGreen)
                .padding(horizontal = 20.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (state.error != null) {
                    Icons.Default.Warning
                } else {
                    Icons.Default.Check
                },
                contentDescription = "Message bar icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if (state.error != null) errorMessage else state.message.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}