package za.co.jacobs.mj.googleonetapsignin.presentation.profile

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.*

/**
 * Created by MJ Jacobs on 2023/12/04 at 18:34
 */

@Composable
fun ProfileScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                onSave = {

                },
                onDeleteAllConfirmed = {

                }
            )
        },
        content = {_->

        }
    )
}