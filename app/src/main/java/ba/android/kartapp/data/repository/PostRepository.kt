package ba.android.kartapp.data.repository

import ba.android.kartapp.data.model.Post
import ba.android.kartapp.data.model.PostDao
import ba.android.kartapp.data.model.toPost
import ba.android.kartapp.data.model.toPostEntity
import javax.inject.Inject

class PostRepository @Inject constructor(private val postDao: PostDao) {

    suspend fun getPosts(): List<Post> = postDao.select().toPost()

    suspend fun addPost(post: Post) {
        postDao.insert(post.toPostEntity())
    }

    suspend fun getPost(id: Long) = postDao.select(id).toPost()

}