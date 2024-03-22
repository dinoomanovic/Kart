package ba.android.kartapp.data.usecases

import android.util.Log
import ba.android.kartapp.data.model.Product
import ba.android.kartapp.data.model.ProductEntity
import ba.android.kartapp.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Dino Omanovic <dino.omanovic@propeller.ba>
 * Created on Mar, 22 2024
 */
class DeleteLocalProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : FlowUseCase<Product, Unit>(Dispatchers.IO) {

    override fun run(input: Product): Flow<Operation<Unit>> = flow {
        emit(Operation.loading())
        try {
            productRepository.deleteProduct(input)
            emit(Operation.success(Unit))
        } catch (e: Exception) {
            emit(Operation.failed(e))
        }
    }
}
