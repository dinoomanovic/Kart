package ba.android.kartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ba.android.kartapp.ui.screen.product.ProductViewModel
import ba.android.kartapp.ui.theme.KartAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KartAppTheme {
                App()
            }
        }
    }
}
