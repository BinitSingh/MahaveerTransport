package com.mobile.mahaveertransport.data.model

import com.squareup.moshi.Json

data class VehicleTypeResponse(
    @Json(name = "vehicle_type") val vehicleType: String,
    @Json(name = "id") val id: Int
)
