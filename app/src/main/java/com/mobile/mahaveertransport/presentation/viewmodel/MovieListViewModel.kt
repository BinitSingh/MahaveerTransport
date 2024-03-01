package com.mobile.mahaveertransport.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.domain.usecase.MovieListUseCase
import com.mobile.mahaveertransport.injection.CoroutineScopeDefault
import com.mobile.mahaveertransport.presentation.ViewState
import com.mobile.mahaveertransport.presentation.ViewState.Loading
import com.mobile.mahaveertransport.presentation.ViewState.Success
import com.mobile.mahaveertransport.presentation.ViewState.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: MovieListUseCase,
    @CoroutineScopeDefault
    dispatchers: CoroutineDispatcher
) : BaseViewModel(dispatchers) {

    private val _uiStateFlow =
        MutableStateFlow<ViewState<List<Movie>>>(Loading(true))

    fun fetchMovieList() {
        viewModelScope.launch {
            val movieListWebServiceResponse = useCase()
            getViewStateFlowFromResponse(
                movieListWebServiceResponse
            ).collect { viewState ->
                _uiStateFlow.value = when (viewState) {
                    is Loading -> viewState
                    is Failure -> Failure(viewState.throwable)
                    is Success -> Success(viewState.result)
                }
            }
        }
    }

    fun getViewStateFlow(): StateFlow<ViewState<List<Movie>>> = _uiStateFlow
}