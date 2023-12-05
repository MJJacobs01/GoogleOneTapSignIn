package za.co.jacobs.mj.googleonetapsignin.presentation.profile

import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import za.co.jacobs.mj.googleonetapsignin.R
import za.co.jacobs.mj.googleonetapsignin.component.*
import za.co.jacobs.mj.googleonetapsignin.ui.theme.*

/**
 * Created by MJ Jacobs on 2023/12/04 at 18:35
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    onSave: () -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Profile",
                color = toAppBarContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = toAppBarBackgroundColor
        ),
        actions = {
            ProfileTopBarActions(
                onSave = onSave,
                onDeleteAllConfirmed = onDeleteAllConfirmed
            )
        }
    )
}

@Composable
fun ProfileTopBarActions(
    onSave: () -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        openDialog = openDialog,
        onYesClicked = {
            onDeleteAllConfirmed()
        },
        onDialogClosed = {
            openDialog = false
        }
    )

    SaveAction(onSave = onSave)
    DeleteAllAction(onDeleteAllConfirmed = { openDialog = true })
}

@Composable
fun DeleteAllAction(onDeleteAllConfirmed: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Vertical menu",
            tint = toAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Delete account",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                onClick = {
                    expanded = false
                    onDeleteAllConfirmed()
                }
            )
        }
    }
}

@Composable
fun SaveAction(
    onSave: () -> Unit
) {
    IconButton(onClick = onSave) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_save_24),
            contentDescription = "Save action",
            tint = toAppBarContentColor
        )
    }
}