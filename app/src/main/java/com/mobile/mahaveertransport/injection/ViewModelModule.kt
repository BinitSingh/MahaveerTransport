package com.mobile.mahaveertransport.injection

import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.data.repository.MovieRepositoryImpl
import com.mobile.mahaveertransport.data.webservice.IDataSource
import com.mobile.mahaveertransport.domain.EntityMapper
import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.domain.usecase.MovieListUseCase
import com.mobile.mahaveertransport.injection.CoroutineScopeDefault
import com.mobile.mahaveertransport.injection.CoroutineScopeIO
import com.mobile.mahaveertransport.injection.MovieListMappingAnnotation
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
        dataSource: IDataSource,
        @MovieListMappingAnnotation
        mapper: EntityMapper<MovieItemsListResponse, List<Movie>>
    ): MovieRepositoryImpl =
        MovieRepositoryImpl(dataSource, mapper)

    @Provides
    fun provideMovieListUseCase(
        repository: MovieRepositoryImpl,
        @CoroutineScopeDefault
        dispatcher: CoroutineDispatcher
    ): MovieListUseCase = MovieListUseCase(repository, dispatcher)
}