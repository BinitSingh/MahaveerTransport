package com.mobile.mahaveertransport.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.mobile.mahaveertransport.domain.datamodel.AppDataModel
import com.mobile.mahaveertransport.domain.usecase.AppDataUseCase
import com.mobile.mahaveertransport.injection.CoroutineScopeDefault
import com.mobile.mahaveertransport.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val useCase: AppDataUseCase,
    @CoroutineScopeDefault
    dispatchers: CoroutineDispatcher
): BaseViewModel(dispatchers) {

    private val _uiStateFlow =
        MutableStateFlow<ViewState<AppDataModel>>(ViewState.Loading(true))

    fun getAppData(){
        viewModelScope.launch {
            val appDataResponse = useCase()
            getViewStateFlowFromResponse(
                appDataResponse
            ).collect { viewState ->
               _uiStateFlow.value = when(viewState){
                   is ViewState.Loading -> viewState
                   is ViewState.Failure -> ViewState.Failure(viewState.throwable)
                   is ViewState.Success -> ViewState.Success(viewState.result)
               }
            }
        }
    }


    fun getViewStateFlow(): StateFlow<ViewState<AppDataModel>> = _uiStateFlow
}