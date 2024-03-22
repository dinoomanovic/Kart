package ba.android.kartapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ba.android.kartapp.R
import ba.android.kartapp.ui.components.NavigationIcons

@Composable
fun ThemedPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    KartAppTheme(darkTheme = darkTheme) {
        Surface {
            Column(modifier = Modifier.padding(5.dp)) {
                content()
            }
        }
    }
}

val bottomNavigation = listOf(
    NavigationIcons(R.drawable.ic_queue, { }, "Notes"),
    NavigationIcons(R.drawable.ic_settings, { }, "Profile")
)