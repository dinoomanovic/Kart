package ba.android.kartapp.data.usecases

import ba.android.kartapp.data.model.Post
import ba.android.kartapp.data.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchPostUseCase @Inject constructor(
    private val postRepo: PostRepository
): FlowUseCase<Long, Post>(Dispatchers.IO) {

    override fun run(input: Long): Flow<Operation<Post>> = flow {
        emit(Operation.loading())
        val posts = postRepo.getPost(input)
        emit(Operation.success(posts))
    }

}