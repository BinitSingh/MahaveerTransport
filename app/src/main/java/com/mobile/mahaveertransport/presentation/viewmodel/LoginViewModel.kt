package com.mobile.mahaveertransport.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.mobile.mahaveertransport.domain.datamodel.LoginModel
import com.mobile.mahaveertransport.domain.request.AuthenticationRequest
import com.mobile.mahaveertransport.domain.usecase.AuthenticationUseCase
import com.mobile.mahaveertransport.extension.isValidEmail
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
class LoginViewModel @Inject constructor(
    private val useCase: AuthenticationUseCase,
    @CoroutineScopeDefault
    dispatchers: CoroutineDispatcher
): BaseViewModel(dispatchers) {

    private val _uiStateFlow =
        MutableStateFlow<ViewState<LoginModel>>(ViewState.Loading(false))

    fun doAuthentication(username: String, password: String){
       viewModelScope.launch {
           if (!username.isValidEmail()){
               val exception = Exception("Please enter a valid email Id!")
               ViewState.Failure(exception)
           }else if (password.isEmpty()){
               val exception = Exception("Please enter password!")
               ViewState.Failure(exception)
           }else{
               _uiStateFlow.value = ViewState.Loading(true)
               val response = useCase(AuthenticationRequest(username,password))
               getViewStateFlowFromResponse(response).collect { viewState->
                   _uiStateFlow.value = when(viewState){
                       is ViewState.Loading -> viewState
                       is ViewState.Failure -> ViewState.Failure(viewState.throwable)
                       is ViewState.Success -> ViewState.Success(viewState.result)
                   }
               }
           }
       }
    }

    fun getViewStateFlow(): StateFlow<ViewState<LoginModel>> = _uiStateFlow

}