package com.mobile.mahaveertransport.data.webservice

import com.mobile.mahaveertransport.data.model.AppDataResponse
import com.mobile.mahaveertransport.data.model.AuthenticationResponse
import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.domain.request.AuthenticationRequest
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun getMovieList(): Flow<Result<MovieItemsListResponse>>
    suspend fun getAppData(): Flow<Result<AppDataResponse>>
    suspend fun authenticate(request: AuthenticationRequest):
            Flow<Result<AuthenticationResponse>>
}