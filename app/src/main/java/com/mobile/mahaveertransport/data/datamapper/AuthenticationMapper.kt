package com.mobile.mahaveertransport.data.datamapper

import com.mobile.mahaveertransport.data.model.AuthenticationResponse
import com.mobile.mahaveertransport.domain.datamodel.LoginModel

fun AuthenticationResponse.map(): LoginModel = LoginModel(
    status, message, token
)