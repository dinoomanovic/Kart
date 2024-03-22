package ba.android.kartapp.data.model
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Long = 0,
    val title: String = "",
    val body: String = "",
    val userId: Long = 0
)

fun Post.toPostEntity() = PostEntity(id, title, body, userId)
fun PostEntity.toPost() = Post(id, title, text)
fun List<PostEntity>.toPost() = this.map { it.toPost() }