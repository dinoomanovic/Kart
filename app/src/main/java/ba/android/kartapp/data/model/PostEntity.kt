package ba.android.kartapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val text: String,
    val userId: Long
)