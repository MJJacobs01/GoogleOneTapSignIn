package za.co.jacobs.mj.googleonetapsignin.component

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.*

/**
 * Created by MJ Jacobs on 2023/12/05 at 17:20
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayAlertDialog(
    title: String = "Delete your account?",
    message: String = "Are you sure you want to delete your account?",
    openDialog: Boolean,
    onYesClicked: () -> Unit,
    onDialogClosed: () -> Unit
) {

    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDialogClosed,
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        onDialogClosed()
                    }
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        onDialogClosed()
                    }
                ) {
                    Text(text = "No")
                }
            }
        )
    }
}