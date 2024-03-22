package ba.android.kartapp.data.usecases

import android.util.Log
import ba.android.kartapp.data.model.Product
import ba.android.kartapp.data.repository.PostRepository
import ba.android.kartapp.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
): FlowUseCase<Unit, List<Product>>(Dispatchers.IO) {

    override fun run(input: Unit): Flow<Operation<List<Product>>> = flow {
        emit(Operation.loading())
        val products = productRepository.fetchProducts()
        Log.d("Products", "client get: $products")

        emit(Operation.success(products))
    }
}