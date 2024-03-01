package  com.mobile.mahaveertransport.domain.usecase

import  com.mobile.mahaveertransport.domain.datamodel.Result
import kotlinx.coroutines.flow.Flow

interface IUseCase<in I : Any?, out T : Any>  {

    suspend  fun invoke(
        input: I? = null
    ): Flow<Result<T>>
}