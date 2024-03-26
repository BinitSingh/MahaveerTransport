package com.mobile.mahaveertransport.data.webservice

import com.mobile.mahaveertransport.BuildConfig
import com.mobile.mahaveertransport.data.model.AppDataResponse
import com.mobile.mahaveertransport.data.model.AuthenticationResponse
import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.request.AuthenticationRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MovieApi {
    @GET("/API/MostPopularMovies/{api_key}")
    suspend fun fetchMovieList(
        @Path("api_key") key: String = BuildConfig.API_KEY
    ): Response<MovieItemsListResponse>

    @GET("/v3/d6644a08-0987-44b7-bd77-a7ada6f1a7b0")
    suspend fun getAppData(): Response<AppDataResponse>

    @POST("api/authentication")
    suspend fun authentication(@Body request: AuthenticationRequest):
            Response<AuthenticationResponse>
}