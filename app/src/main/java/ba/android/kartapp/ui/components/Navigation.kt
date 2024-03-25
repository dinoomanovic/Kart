package ba.android.kartapp.ui.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ba.android.kartapp.R
import ba.android.kartapp.ui.drawer.DrawerBody
import ba.android.kartapp.ui.drawer.TopBar
import ba.android.kartapp.ui.drawer.drawerScreens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(
    navHostController: NavHostController,
    title: String,
    topIcons: List<NavigationIcons>,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
    onBack: (() -> Unit)? = null,
    bottomIcons: List<NavigationIcons>? = null,
    modifier: Modifier = Modifier
) {
    val kart = stringResource(id = R.string.kart)

    val scope = rememberCoroutineScope()
    val selectedDrawerItem = remember {
        mutableStateOf(kart)
    }

    selectedDrawerItem.value = title
    Column(modifier = modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                PermanentDrawerSheet(drawerContainerColor = Color.Black) {
                    DrawerBody(
                        menuItems = drawerScreens(),
                        drawerState = drawerState,
                        scope = scope,
                        selectedDrawerItem = selectedDrawerItem.value,
                    ) {
                        selectedDrawerItem.value = it.id
                        navHostController.navigate(it.navId)
                    }
                }

            }) {
            Scaffold(
                topBar = {
                    TopBar(
                        isBackEnabled = false,
                        navigateBack = onBack ?: {},
                        title = title,
                        topIcons = topIcons,
                        openDrawer =
                        {
                            scope.launch {
                                // Open the drawer with animation
                                // and suspend until it is fully
                                // opened or animation has been canceled
                                drawerState.open()
                            }
                        }
                    )
                },
                bottomBar = {
                    if (bottomIcons?.isNotEmpty() == true) {
                        BottomNavigation(bottomIcons)
                    }
                }
            ) {
                Box(
                    Modifier
                        .padding(top = 56.dp)
                        .weight(1f)
                        .background(Color.White)) {
                    content()

                }

            }
        }

    }
}

data class NavigationIcons(
    @DrawableRes val image: Int,
    val onClick: () -> Unit,
    val text: String = ""
)

@Composable
fun NavigationHeader(
    onBack: (() -> Unit)?,
    title: String,
    icons: List<NavigationIcons>
) {
    Surface(
        color = Color.Black,
        contentColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            if (onBack != null) {
                IconButton(
                    image = R.drawable.ic_back, onClick = onBack,
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
            Text(
                text = title,
                color = Color.White
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                for (icon in icons) {
                    Spacer(modifier = Modifier.width(20.dp))
                    IconButton(
                        image = icon.image,
                        onClick = icon.onClick,
                        color = Color.Transparent
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
fun BottomNavigation(icons: List<NavigationIcons>) {
    Surface(
        color = Color.Black,
        contentColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (icon in icons) {
                IconButton(
                    modifier = Modifier.weight(1f),
                    image = icon.image,
                    text = icon.text,
                    onClick = icon.onClick,
                    color = Color.White
                )
            }
        }
    }
}
