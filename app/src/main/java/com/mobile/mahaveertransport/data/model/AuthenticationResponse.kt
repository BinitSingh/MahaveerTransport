package com.mobile.mahaveertransport.data.model

import com.squareup.moshi.Json

data class AuthenticationResponse(
    @Json(name = "status") val status: Boolean,
    @Json(name = "msg") val message: String,
    @Json(name = "token") val token: String,
)
