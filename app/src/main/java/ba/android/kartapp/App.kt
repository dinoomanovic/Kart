package ba.android.kartapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import ba.android.kartapp.ui.theme.KartAppTheme
import ba.android.kartapp.ui.NavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun App() {
    KartAppTheme {
        val systemUiController = rememberSystemUiController()
        val isDarkTheme = isSystemInDarkTheme()
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = !isDarkTheme)
        }

        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}
