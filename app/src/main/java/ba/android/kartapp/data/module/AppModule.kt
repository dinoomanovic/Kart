package ba.android.kartapp.data.module
import ba.android.kartapp.data.model.ProductDao
import ba.android.kartapp.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    fun provideProductRepository(client: HttpClient, productDao: ProductDao): ProductRepository {
        return ProductRepository(client, productDao)
    }
}
