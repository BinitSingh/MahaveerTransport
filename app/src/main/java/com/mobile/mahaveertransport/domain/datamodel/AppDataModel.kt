package com.mobile.mahaveertransport.domain.datamodel

data class AppDataModel(
    val vehicleTypes: List<VehicleTypeModel>,
    val stations: List<StationModel>,
    val states: List<StateModel>
)
