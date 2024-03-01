package com.mobile.mahaveertransport.injection

import com.mobile.mahaveertransport.BuildConfig
import com.mobile.mahaveertransport.data.webservice.IDataSource
import com.mobile.mahaveertransport.data.webservice.MovieApi
import com.mobile.mahaveertransport.data.webservice.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import com.mobile.mahaveertransport.utility.Constants.CALL_TIMEOUT
import com.mobile.mahaveertransport.utility.Constants.CONNECT_TIMEOUT
import com.mobile.mahaveertransport.utility.Constants.READ_TIMEOUT
import com.mobile.mahaveertransport.utility.Constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOKHttpClient(interceptor: Interceptor) = OkHttpClient().apply {
        OkHttpClient.Builder().apply {
            callTimeout(CALL_TIMEOUT, SECONDS)
            connectTimeout(CONNECT_TIMEOUT, SECONDS)
            readTimeout(READ_TIMEOUT, SECONDS)
            writeTimeout(WRITE_TIMEOUT, SECONDS)
            addInterceptor(interceptor)
            build()
        }
    }

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineDispatcherIO() = Dispatchers.IO

    @CoroutineScopeDefault
    @Provides
    fun provideCoroutineDispatcherDefault() = Dispatchers.Default

    @Provides
    fun provideRemoteDataSource(
        retrofit: MovieApi,
        @CoroutineScopeIO
        dispatcher: CoroutineDispatcher
    ): IDataSource = RemoteDataSource(retrofit,dispatcher)
}