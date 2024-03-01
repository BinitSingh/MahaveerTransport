package  com.mobile.mahaveertransport.domain.repository

import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.domain.datamodel.Result
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getMovieList(): Flow<Result<List<Movie>>>
}