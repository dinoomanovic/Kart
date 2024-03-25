package ba.android.kartapp.ui.screen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ba.android.kartapp.R
import ba.android.kartapp.ui.components.BottomNavigation
import ba.android.kartapp.ui.components.Navigation
import ba.android.kartapp.ui.components.NavigationIcons
import ba.android.kartapp.ui.screen.loadingscreen.LoadingScreen
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    bottomNavigation: List<NavigationIcons>,
    navigateToProductDetails: () -> Unit
) {
    val drawerState: DrawerState = DrawerState(DrawerValue.Closed)
    Navigation(
        navHostController = navHostController,
        title = stringResource(R.string.home),
        topIcons = listOf(),
        bottomIcons = bottomNavigation,
        drawerState = drawerState,
        content = {
            HomeScreen(navigateToProductDetails = navigateToProductDetails)
        })
}

@Composable
fun HomeScreen(navigateToProductDetails: ()-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Kart", color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_cart),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )
    }
}
