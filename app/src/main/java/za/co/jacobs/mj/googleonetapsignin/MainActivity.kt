package za.co.jacobs.mj.googleonetapsignin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import za.co.jacobs.mj.googleonetapsignin.navigation.*
import za.co.jacobs.mj.googleonetapsignin.ui.theme.GoogleOneTapSignInTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleOneTapSignInTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}