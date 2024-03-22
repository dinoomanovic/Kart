package ba.android.kartapp.data.model
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String
)

fun Product.toProductEntity() = ProductEntity(id.toLong(), name, description, price, image)
fun ProductEntity.toProduct() = Product(id.toInt(), name, description, price, image)
fun List<ProductEntity>.toProduct() = this.map { it.toProduct() }