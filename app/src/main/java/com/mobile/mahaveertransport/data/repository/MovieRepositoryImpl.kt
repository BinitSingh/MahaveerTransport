package com.mobile.mahaveertransport.data.repository


import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.data.webservice.IDataSource
import com.mobile.mahaveertransport.domain.EntityMapper
import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.domain.repository.IMovieRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiCall: IDataSource,
    private val entityMapper: EntityMapper<MovieItemsListResponse, List<Movie>>
    ) : IMovieRepository {

    override suspend fun getMovieList(): Flow<Result<List<Movie>>> {
        return apiCall.getMovieList().map {
            when (it) {
                is Result.Success -> {
                    Result.Success(entityMapper.transformFrom(it.data))
                }
                is Result.Failure -> {
                    it
                }
            }
        }
    }
}