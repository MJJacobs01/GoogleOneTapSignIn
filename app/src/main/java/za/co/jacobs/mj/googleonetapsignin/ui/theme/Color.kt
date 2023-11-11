package za.co.jacobs.mj.googleonetapsignin.ui.theme

import androidx.compose.foundation.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val ErrorRed = Color(0xFFFF6c60)
val InfoGreen = Color(0xFF00c096)
val LoadingBlue = Color(0xFF1a73e8)
val Gray500 = Color(0xFF7C7980)

val toAppBarContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.LightGray

val toAppBarBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Gray500