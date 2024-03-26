package com.mobile.mahaveertransport.data.model

import com.squareup.moshi.Json

data class StateResponse(
    @Json(name = "zone") val zone: String,
    @Json(name = "name") val name: String,
    @Json(name = "id") val id: String,
    @Json(name = "state_code") val state_code: String,
)
