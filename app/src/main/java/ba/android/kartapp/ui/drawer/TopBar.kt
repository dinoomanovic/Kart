package ba.android.kartapp.ui.drawer

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.android.kartapp.R
import ba.android.kartapp.ui.components.IconButton
import ba.android.kartapp.ui.components.MyIcon
import ba.android.kartapp.ui.components.NavigationHeader
import ba.android.kartapp.ui.components.NavigationIcons
import ba.android.kartapp.ui.components.debouncedClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isBackEnabled: Boolean = true,
    title: String,
    openDrawer: () -> Unit,
    navigateBack: () -> Unit,
    topIcons: List<NavigationIcons>
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = if (isBackEnabled) Icons.Filled.ArrowBack else Icons.Default.Menu,
                            tint = Color.White,
                            modifier = Modifier
                                .debouncedClickable {
                                    if (isBackEnabled) navigateBack() else openDrawer()
                                },
                            contentDescription = null
                        )
                        NavigationHeader(null, title, topIcons)

                    }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(
                                image = getIconFromTitle(context, title),
                                color = Color.White,
                                onClick = {},
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .size(36.dp)
                               )
                        }

                },
            )
        }
    }
}

fun getIconFromTitle(context: Context, title: String):Int {
    return when(title) {
        context.getString(R.string.home) -> R.drawable.ic_home
        context.getString(R.string.product_details) -> R.drawable.ic_queue
        context.getString(R.string.kart) -> R.drawable.ic_product_cart
        else -> R.drawable.ic_home
    }
}

@Preview
@Composable
fun TopBarPreview() {
    val kart = stringResource(id = R.string.kart)
    TopBar(
        title = kart,
        navigateBack = {},
        topIcons = listOf(),
        openDrawer = { /*TODO*/ })
}