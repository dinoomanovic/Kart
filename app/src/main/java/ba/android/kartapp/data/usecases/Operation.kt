package ba.android.kartapp.data.usecases

sealed class Operation<out T> {
    object Loading : Operation<Nothing>()
    data class Success<T>(val data: T) : Operation<T>()
    data class Failed(val throwable: Throwable) : Operation<Nothing>()

    override fun toString(): String = when (this) {
        is Failed -> "Failed: $throwable"
        is Loading -> "Loading"
        is Success -> "Success: ${data.toString()}"
    }

    companion object {
        fun loading() = Loading
        fun <T> success(data: T) = Success(data)
        fun failed(throwable: Throwable) = Failed(throwable)
    }
}