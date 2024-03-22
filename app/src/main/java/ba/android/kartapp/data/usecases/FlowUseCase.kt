package ba.android.kartapp.data.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import timber.log.Timber

abstract class FlowUseCase<in Input, Output>(private val coroutineDispatcher: CoroutineDispatcher) {

    protected abstract fun run(input: Input): Flow<Operation<Output>>

    private fun runWithTimeout(input: Input): Flow<Operation<Output>> = flow {
        withTimeout(TIMEOUT_IN_MILLIS) {
            emitAll(run(input))
        }
    }

    fun prepare(input: Input, withTimeout: Boolean = false): Flow<Operation<Output>> {
        val flow = if (withTimeout) runWithTimeout(input) else run(input)

        return flow
            .catch { error ->
                Timber.e(error)
                emit(Operation.failed(error))
            }
            .flowOn(coroutineDispatcher)
    }

    companion object {
        const val TIMEOUT_IN_MILLIS = 30000L
    }
}
