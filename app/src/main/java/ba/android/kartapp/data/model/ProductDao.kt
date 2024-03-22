package ba.android.kartapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun select(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun select(id: Long): ProductEntity

    @Query("DELETE FROM products WHERE id == :id")
    suspend fun delete(id: Long)
}
