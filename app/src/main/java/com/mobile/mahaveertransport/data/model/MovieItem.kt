package com.mobile.mahaveertransport.data.model

import com.squareup.moshi.Json

data class MovieItem(
    @Json(name = "id")
    val id : String?,
    @Json(name = "rank")
    val rank : String?,
    @Json(name = "title")
    val title : String?,
    @Json(name = "fullTitle")
    val fullTitle : String?,
    @Json(name = "year")
    val year : String?,
    @Json(name = "image")
    val image : String?,
    @Json(name = "crew")
    val crew : String?,
    @Json(name = "imDbRating")
    val imDbRating : String?,
    @Json(name = "imDbRatingCount")
    val imDbRatingCount : String?
)
