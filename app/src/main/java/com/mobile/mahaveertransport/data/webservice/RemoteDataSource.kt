package com.mobile.mahaveertransport.data.webservice

import com.mobile.mahaveertransport.data.model.AppDataResponse
import com.mobile.mahaveertransport.data.model.AuthenticationResponse
import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.domain.request.AuthenticationRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val client: MovieApi,
    private val dispatcher: CoroutineDispatcher
): IDataSource {

    override suspend fun getMovieList(): Flow<Result<MovieItemsListResponse>> {
        return flow {
            try {
                val response = client.fetchMovieList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Result.Success(it))
                    } ?:
                    emit(Result.Failure(IOException(response.message())))
                    return@flow
                } else {
                    emit(Result.Failure(IOException()))
                    return@flow
                }
            }catch (exception: Exception){
                emit(Result.Failure(exception))
            }
        }.flowOn(dispatcher)
    }

    override suspend fun getAppData(): Flow<Result<AppDataResponse>> {
        return flow {
            try {
                val response = client.getAppData()
                if (response.isSuccessful){
                    response.body()?.let {
                        emit(Result.Success(it))
                    }?:
                    emit(Result.Failure(IOException(response.message())))
                    return@flow
                }else{
                    emit(Result.Failure(IOException()))
                    return@flow
                }
            }catch (exception: Exception){
                emit(Result.Failure(exception))
                return@flow
            }
        }.flowOn(dispatcher)
    }

    override suspend fun authenticate(request: AuthenticationRequest):
            Flow<Result<AuthenticationResponse>> {
        return flow {
            try {
                val response = client.authentication(request)
                if (response.isSuccessful){
                    response.body()?.let {
                        emit(Result.Success(it))
                    }?:
                    emit(Result.Failure(IOException(response.message())))
                    return@flow
                }else{
                    emit(Result.Failure(IOException()))
                    return@flow
                }
            }catch (exception: Exception){
                emit(Result.Failure(exception))
                return@flow
            }
        }.flowOn(dispatcher)
    }
}