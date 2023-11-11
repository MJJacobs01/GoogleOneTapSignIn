package za.co.jacobs.mj.googleonetapsignin.presentation.screen.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.*
import za.co.jacobs.mj.googleonetapsignin.ui.theme.*

/**
 * Created by MJ Jacobs on 2023/11/11 at 15:08
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopBar() {
    TopAppBar(
        title = {
            Text(text = "Sign in")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = toAppBarBackgroundColor,
            titleContentColor = toAppBarContentColor
        )
    )
}

@Preview
@Composable
fun PreviewTopAppBar() {
    LoginTopBar()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopAppBarDarkTheme() {
    LoginTopBar()
}