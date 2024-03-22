package ba.android.kartapp.data.repository

import android.util.Log
import ba.android.kartapp.data.model.Post
import ba.android.kartapp.data.model.PostDao
import ba.android.kartapp.data.model.Product
import ba.android.kartapp.data.model.ProductDao
import ba.android.kartapp.data.model.ProductEntity
import ba.android.kartapp.data.model.toProductEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor(private val client: HttpClient, private val productDao: ProductDao) {

    suspend fun fetchProducts(): List<Product> = withContext(Dispatchers.IO) {
        try {
            val posts: List<Post> = client.get("https://jsonplaceholder.typicode.com/posts") {
                contentType(ContentType.Application.Json)
            }
            Log.d("ProductRepository", "Fetched posts: ${posts.size}")

            // Transform the posts into food products
            posts.map { post ->
                Product(
                    id = post.id.toInt(),
                    name = post.title,
                    description = post.body,
                    price = post.userId.toDouble(),
                    image = "https://static.vecteezy.com/system/resources/thumbnails/008/133/644/small_2x/shopping-cart-icon-design-templates-free-vector.jpg"
                )
            }
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products", e)
            emptyList() // Return an empty list or handle the error appropriately
        }
    }

    suspend fun insertProduct(product: Product) {
        try {
            productDao.insert(product.toProductEntity())
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error inserting products", e)
        }
    }
    suspend fun deleteProduct(product: Product) {
        try {
            productDao.delete(product.toProductEntity().id)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error inserting products", e)
        }
    }

    suspend fun fetchLocalProducts(): List<ProductEntity>  {
        return try {
            productDao.select()
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching local products", e)
            emptyList() // Return an empty list or handle the error appropriately
        }
    }
}
