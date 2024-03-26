package com.mobile.mahaveertransport.domain.usecase

import com.mobile.mahaveertransport.domain.datamodel.AppDataModel
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataUseCase @Inject constructor(
    private val repository: IMovieRepository,
    private val dispatcher: CoroutineDispatcher
): IUseCase<Any?, AppDataModel>{

    override suspend operator fun invoke(input: Any?): Flow<Result<AppDataModel>> =
        flow {
            val response = repository.getAppData()
            response.map {
                when(it){
                    is Result.Success -> {
                        Result.Success(it.data)
                    }
                    is Result.Failure -> {
                        Result.Failure(it.throwable)
                    }
                }
            }.collect {
                emit(it)
            }
            return@flow
        }.flowOn(dispatcher)
}