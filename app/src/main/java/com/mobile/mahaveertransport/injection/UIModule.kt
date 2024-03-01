package com.mobile.mahaveertransport.injection

import com.mobile.mahaveertransport.presentation.view.adapter.MovieListAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object UIModule {
    @Provides
    fun provideMovieListAdaptor(): MovieListAdaptor{
        return MovieListAdaptor()
    }
}