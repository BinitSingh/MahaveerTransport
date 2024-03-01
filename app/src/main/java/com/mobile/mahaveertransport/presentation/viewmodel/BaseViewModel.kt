package com.mobile.mahaveertransport.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.presentation.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

open class BaseViewModel(
    private val dispatchers: CoroutineDispatcher
) : ViewModel() {
    suspend fun <T : Any> getViewStateFlowFromResponse(
        response: Flow<Result<T>>,
    ) : Flow<ViewState<T>> {
        return flow {
            emit(ViewState.Loading(true))
            response.map {
                when (it) {
                    is Result.Success ->
                        ViewState.Success(it.data)
                    is Result.Failure ->
                        ViewState.Failure(it.throwable)
                }
            }.collect {
                emit(it)
            }
            emit(ViewState.Loading(false))
        }.flowOn(dispatchers)
    }
}


