package com.mobile.mahaveertransport.data.model

import com.squareup.moshi.Json

data class AppDataResponse(
    @Json(name = "vehicleTypes") val vehicleTypes: List<VehicleTypeResponse>,
    @Json(name = "stations") val stations: List<StationResponse>,
    @Json(name = "states") val states: List<StateResponse>
)
