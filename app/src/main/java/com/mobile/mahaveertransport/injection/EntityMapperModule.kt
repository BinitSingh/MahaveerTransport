package com.mobile.mahaveertransport.injection

import com.mobile.mahaveertransport.data.datamapper.MovieListMapper
import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.EntityMapper
import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.injection.MovieListMappingAnnotation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object EntityMapperModule {

    @Provides
    @MovieListMappingAnnotation
    fun provideEntityMapper(): EntityMapper<MovieItemsListResponse, List<Movie>> =
        MovieListMapper()
}