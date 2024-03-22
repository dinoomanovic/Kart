package ba.android.kartapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: PostEntity)

    @Query("SELECT * FROM PostEntity")
    suspend fun select(): List<PostEntity>

    @Query("SELECT * FROM PostEntity WHERE id = :id")
    suspend fun select(id: Long): PostEntity

    @Query("DELETE FROM PostEntity WHERE id == :id")
    suspend fun delete(id: String)
}