package com.mobile.mahaveertransport.data.datamapper

import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.EntityMapper
import com.mobile.mahaveertransport.domain.datamodel.Movie


class MovieListMapper: EntityMapper<MovieItemsListResponse, List<Movie>> {
    override fun transformFrom(entity: MovieItemsListResponse): List<Movie> {
        val movieList = mutableListOf<Movie>()
        entity.items?.forEach { movieItem ->
            movieItem.apply {
                movieList.add(
                    Movie(
                        id = id,
                        title = title,
                        year = year,
                        image = image,
                        crew = crew,
                        imDbRating = imDbRating,
                        imDbRatingCount = imDbRatingCount
                    )
                )
            }
        }
        return movieList
    }
}