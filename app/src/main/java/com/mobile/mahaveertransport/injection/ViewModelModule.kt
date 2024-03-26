package com.mobile.mahaveertransport.injection

import com.mobile.mahaveertransport.data.repository.MovieRepositoryImpl
import com.mobile.mahaveertransport.data.webservice.IDataSource
import com.mobile.mahaveertransport.domain.repository.IMovieRepository
import com.mobile.mahaveertransport.domain.usecase.AppDataUseCase
import com.mobile.mahaveertransport.domain.usecase.AuthenticationUseCase
import com.mobile.mahaveertransport.domain.usecase.MovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMovieRepository(
        dataSource: IDataSource
    ): IMovieRepository =
        MovieRepositoryImpl(dataSource)

    @Provides
    fun provideMovieListUseCase(
        repository: MovieRepositoryImpl,
        @CoroutineScopeDefault
        dispatcher: CoroutineDispatcher
    ): MovieListUseCase = MovieListUseCase(repository, dispatcher)

    @Provides
    fun provideAuthenticationUseCase(
        repository: IMovieRepository,
        @CoroutineScopeDefault
        dispatcher: CoroutineDispatcher
    ): AuthenticationUseCase = AuthenticationUseCase(repository, dispatcher)

    @Provides
    fun provideAppDataUseCase(
        repository: IMovieRepository,
        @CoroutineScopeDefault
        dispatcher: CoroutineDispatcher
    ): AppDataUseCase = AppDataUseCase(repository, dispatcher)
}