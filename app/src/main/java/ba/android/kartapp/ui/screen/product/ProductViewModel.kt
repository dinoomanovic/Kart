package ba.android.kartapp.ui.screen.product

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.android.kartapp.data.model.Product
import ba.android.kartapp.data.usecases.FetchProductsUseCase
import ba.android.kartapp.data.usecases.InsertProductUseCase
import ba.android.kartapp.data.usecases.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsUseCase: FetchProductsUseCase,
    private val insertProductUseCase: InsertProductUseCase
) : ViewModel() {

    var state by mutableStateOf(ProductModel())

    data class ProductModel(
        val isLoading: Boolean = false,
        val products: List<Product> = listOf(),
    )

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            productsUseCase.prepare(Unit).collect { result ->
                when (result) {
                    is Operation.Failed -> {
                        delay(2000)
                        fetchProducts()
                    }

                    is Operation.Success -> state = ProductModel(products = result.data)
                    is Operation.Loading -> state = ProductModel(isLoading = true)
                    else -> {}
                }
            }
        }
    }

    fun insertProduct(context: Context, product: Product) {
        viewModelScope.launch {
            insertProductUseCase.prepare(product).collect { result ->
                when (result) {
                    is Operation.Failed -> {
                        delay(2000)
                        insertProduct(context, product)
                    }

                    is Operation.Success -> {
                        Toast.makeText(
                            context,
                            "Product successfully added to cart!",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is Operation.Loading -> {}
                    else -> {}
                }
            }
        }
    }

}
