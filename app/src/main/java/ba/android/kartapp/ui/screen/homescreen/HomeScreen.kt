package ba.android.kartapp.ui.screen.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ba.android.kartapp.R
import ba.android.kartapp.ui.components.Navigation
import ba.android.kartapp.ui.components.NavigationIcons
import ba.android.kartapp.ui.screen.loadingscreen.LoadingScreen
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    bottomNavigation: List<NavigationIcons>,
    navigateToProductDetails: () -> Unit
) {
    Navigation(
        title = stringResource(R.string.home),
        topIcons = listOf(),
        bottomIcons = bottomNavigation,
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
        Text(text = "Welcome to Kart")
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_cart),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )
    }
//    LaunchedEffect(Unit) {
//        delay(2000)
//        navigateToProductDetails()
//    }
}
