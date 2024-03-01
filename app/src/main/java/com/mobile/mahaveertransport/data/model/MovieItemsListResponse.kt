package com.mobile.mahaveertransport.data.model

import com.squareup.moshi.Json

data class MovieItemsListResponse(
    @Json(name = "items") val items: List<MovieItem>? = null,
    @Json(name = "errorMessage") val errorMessage: String? = null
)
