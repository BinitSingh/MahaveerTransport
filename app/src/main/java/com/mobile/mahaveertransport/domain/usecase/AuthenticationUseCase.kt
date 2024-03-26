package com.mobile.mahaveertransport.domain.usecase

import com.mobile.mahaveertransport.domain.datamodel.LoginModel
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.domain.repository.IMovieRepository
import com.mobile.mahaveertransport.domain.request.AuthenticationRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val repository: IMovieRepository,
    private val dispatcher: CoroutineDispatcher
) : IUseCase<AuthenticationRequest, LoginModel> {

    override suspend operator fun invoke(input: AuthenticationRequest?): Flow<Result<LoginModel>> =
        flow {
            input?.let { request ->
                val response = repository.authenticate(request)
                response.map {
                    when(it){
                        is Result.Success -> {
                            Result.Success(it.data)
                        }
                        is Result.Failure -> {
                            Result.Failure(it.throwable)
                        }
                    }
                }.collect<Result<LoginModel>> {
                    emit(it)
                }
                return@flow
            }
        }.flowOn(dispatcher)


}