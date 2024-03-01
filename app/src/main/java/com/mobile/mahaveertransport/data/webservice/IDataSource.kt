package com.mobile.mahaveertransport.data.webservice

import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.datamodel.Result
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun getMovieList(): Flow<Result<MovieItemsListResponse>>
}