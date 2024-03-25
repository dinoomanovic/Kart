package ba.android.kartapp.ui.screen.product

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ba.android.kartapp.R
import ba.android.kartapp.data.model.Product
import ba.android.kartapp.ui.components.Navigation
import ba.android.kartapp.ui.components.NavigationIcons
import ba.android.kartapp.ui.screen.loadingscreen.LoadingScreen
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun ProductScreen(
    navHostController: NavHostController,
    viewModel: ProductViewModel,
    bottomNavigation: List<NavigationIcons>,
    navigateToCart: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    val state = viewModel.state
    val drawerState: DrawerState = DrawerState(DrawerValue.Closed)

    Navigation(
        navHostController = navHostController,
        title = stringResource(R.string.product_details),
        topIcons = listOf(NavigationIcons(R.drawable.ic_queue, {})),
        bottomIcons = bottomNavigation,
        drawerState = drawerState,
        content = {
            if (state.isLoading)
                LoadingScreen()
            else
                ProductScreenView(viewModel, state.products, navigateToCart)
        })
}

@Composable
fun ProductScreenView(
    viewModel: ProductViewModel,
    products: List<Product>,
    navigateToCart: () -> Unit
) {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
        ) {
            items(products) { product ->
                ProductView(product) {
                    viewModel.insertProduct(context, product)
                    //navigateToCart()
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductView(product: Product, onClick: () -> Unit) {
    val context = LocalContext.current
    val screenHeight = context.resources.displayMetrics.heightPixels.dp
    val screenWidth = context.resources.displayMetrics.widthPixels.dp
    val bottomNavHeight = 56.dp // Adjust based on your bottom navigation bar height

    // State to control the position of the image
    val animatedOffsetX = remember { Animatable(0f) }
    val animatedOffsetY = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()


    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 0.dp)
            .clickable {
                scope.launch {
                    // Calculate the target position for the animation
                    val targetX = (screenWidth.value / 1.5)  // Assuming the cart is the third item
                    val targetY = screenHeight.value - bottomNavHeight.value

                    // Animate the image to the cart's position
                    animatedOffsetX.animateTo(
                        targetValue = targetX.toFloat(),
                        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                    animatedOffsetY.animateTo(
                        targetValue = targetY,
                        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                    // After the animation, reset the position back to the original place
                    animatedOffsetX.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 0, easing = LinearEasing)
                    )
                    animatedOffsetY.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 0, easing = LinearEasing)
                    )
                    onClick()
                }

            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Image(
                    painter = rememberImagePainter(data = product.image),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                )
                if (animatedOffsetY.value > 0f)
                    Image(
                        painter = rememberImagePainter(data = product.image),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(bottom = 10.dp)
                            .offset(
                                x = with(LocalDensity.current) { animatedOffsetX.value.toDp() },
                                y = with(LocalDensity.current) { animatedOffsetY.value.toDp() }) // Convert Float to Dp
                    )
            }
            Column {
                Text(
                    text = product.name,
                    fontSize = 12.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 10.sp,
                    color = Color.DarkGray,
                    text = product.description,
                    textAlign = TextAlign.Justify
                )
            }
        }

        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(1.dp)
        )
    }
}
