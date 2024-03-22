package ba.android.kartapp.data.usecases

import ba.android.kartapp.data.model.Post
import ba.android.kartapp.data.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
): FlowUseCase<Unit, List<Post>>(Dispatchers.IO) {

    override fun run(input: Unit): Flow<Operation<List<Post>>> = flow {
        emit(Operation.loading())
        val posts = postRepository.getPosts()
        emit(Operation.success(posts))
    }

}