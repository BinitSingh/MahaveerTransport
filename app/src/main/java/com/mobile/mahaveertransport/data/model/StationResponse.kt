package com.mobile.mahaveertransport.data.model

import com.squareup.moshi.Json

data class StationResponse(
    @Json(name = "station_name") val stationName: String,
    @Json(name = "id") val id: Int
)
