package  com.mobile.mahaveertransport.domain.datamodel

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure(val throwable: Throwable) : Result<Nothing>()
}