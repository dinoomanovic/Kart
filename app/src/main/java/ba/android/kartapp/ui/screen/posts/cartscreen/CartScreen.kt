package ba.android.kartapp.ui.screen.posts.cartscreen

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.android.kartapp.R
import ba.android.kartapp.data.model.ProductEntity
import ba.android.kartapp.data.model.toProduct
import ba.android.kartapp.ui.components.IconButton
import ba.android.kartapp.ui.components.Navigation
import ba.android.kartapp.ui.components.NavigationIcons
import ba.android.kartapp.ui.screen.loadingscreen.LoadingScreen
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    bottomNavigation: List<NavigationIcons>,
    navigateToProductDetails:() -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchSavedProducts()
    }

    val state = viewModel.state

    Navigation(
        title = stringResource(R.string.kart),
        topIcons = listOf(NavigationIcons(R.drawable.ic_product_cart, {})),
        bottomIcons = bottomNavigation,
        content = {
            if (state.isLoading)
                LoadingScreen()
            else
                CartScreenView(viewModel, state.products, navigateToProductDetails)
        })
}

@Composable
fun CartScreenView(
    viewModel: CartViewModel,
    products: List<ProductEntity>,
    navigateToProductDetails: () -> Unit
) {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (products.isEmpty()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                IconButton(
                    image = R.drawable.ic_product_cart,
                    onClick = {}
                )
                Text(text = "Cart is Empty!")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
            ) {
                items(products) { product ->
                    ProductView(product) {
                        viewModel.deleteLocalProduct(context, product.toProduct())
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductView(product: ProductEntity, onClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 0.dp)
            .clickable { onClick() }
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