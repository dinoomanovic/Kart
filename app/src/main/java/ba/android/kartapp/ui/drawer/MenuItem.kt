package ba.android.kartapp.ui.drawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ba.android.kartapp.R
import ba.android.kartapp.ui.MainDestinations

data class MenuItem(
    val navId: String,
    val id: String,
)

@Composable
fun drawerScreens() = listOf(
    MenuItem(navId = MainDestinations.HOME, stringResource(id = R.string.home)),
    MenuItem(navId = MainDestinations.PRODUCTS, stringResource(id = R.string.product_details)),
    MenuItem(navId = MainDestinations.CART, stringResource(id = R.string.kart)),
)