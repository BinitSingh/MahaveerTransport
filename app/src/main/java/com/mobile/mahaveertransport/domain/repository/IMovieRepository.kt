package  com.mobile.mahaveertransport.domain.repository

import com.mobile.mahaveertransport.domain.datamodel.AppDataModel
import com.mobile.mahaveertransport.domain.datamodel.LoginModel
import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.domain.request.AuthenticationRequest
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getMovieList(): Flow<Result<List<Movie>>>
    suspend fun authenticate(request: AuthenticationRequest): Flow<Result<LoginModel>>
    suspend fun getAppData(): Flow<Result<AppDataModel>>
}