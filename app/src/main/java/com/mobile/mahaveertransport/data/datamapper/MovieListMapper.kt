package com.mobile.mahaveertransport.data.datamapper

import com.mobile.mahaveertransport.data.model.MovieItemsListResponse
import com.mobile.mahaveertransport.domain.datamodel.Movie

fun MovieItemsListResponse.map(): List<Movie>{
    val movieList = mutableListOf<Movie>()
    this.items?.forEach { movieItem ->
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