package ba.android.kartapp.data.usecases

import android.util.Log
import ba.android.kartapp.data.model.Product
import ba.android.kartapp.data.model.ProductEntity
import ba.android.kartapp.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchLocalProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : FlowUseCase<Unit, List<ProductEntity>>(Dispatchers.IO) {

    override fun run(input: Unit): Flow<Operation<List<ProductEntity>>> = flow {
        emit(Operation.loading())
        val products = productRepository.fetchLocalProducts()
        Log.d("Products local", "client get: $products")

        emit(Operation.success(products))
    }
}