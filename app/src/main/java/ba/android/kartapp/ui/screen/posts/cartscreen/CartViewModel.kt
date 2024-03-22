package ba.android.kartapp.ui.screen.posts.cartscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.android.kartapp.data.model.Product
import ba.android.kartapp.data.model.ProductEntity
import ba.android.kartapp.data.usecases.DeleteLocalProductUseCase
import ba.android.kartapp.data.usecases.FetchLocalProductsUseCase
import ba.android.kartapp.data.usecases.Operation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val fetchSavedProducts: FetchLocalProductsUseCase,
    private val deleteLocalProductUseCase: DeleteLocalProductUseCase
) : ViewModel() {

    data class ProductModel(
        val isLoading: Boolean = false,
        val products: List<ProductEntity> = listOf(),
    )

    var state by mutableStateOf(ProductModel())

    fun fetchSavedProducts() {
        viewModelScope.launch {
            fetchSavedProducts.prepare(Unit).collect { result ->
                when (result) {
                    is Operation.Loading -> state = state.copy(isLoading = true)
                    is Operation.Failed -> {
                        delay(2000)
                        fetchSavedProducts()
                    }

                    is Operation.Success -> state =
                        state.copy(isLoading = false, products = result.data)
                }
            }
        }
    }

    fun deleteLocalProduct(context: Context, product: Product) {
        viewModelScope.launch {
            deleteLocalProductUseCase.prepare(product).collect { result ->
                when (result) {
                    is Operation.Failed -> {
                        delay(2000)
                        deleteLocalProduct(context, product)
                    }

                    is Operation.Success -> {
                        Toast.makeText(
                            context,
                            "Product successfully removed from cart!",
                            Toast.LENGTH_LONG
                        ).show()
                        delay(2000)
                        fetchSavedProducts()
                    }

                    is Operation.Loading ->  state.copy(isLoading = true)
                    else -> {}
                }
            }
        }
    }
}
