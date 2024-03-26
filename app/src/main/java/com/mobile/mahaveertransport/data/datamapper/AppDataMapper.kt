package com.mobile.mahaveertransport.data.datamapper

import com.mobile.mahaveertransport.data.model.AppDataResponse
import com.mobile.mahaveertransport.domain.datamodel.AppDataModel
import com.mobile.mahaveertransport.domain.datamodel.StateModel
import com.mobile.mahaveertransport.domain.datamodel.StationModel
import com.mobile.mahaveertransport.domain.datamodel.VehicleTypeModel

fun AppDataResponse.map(): AppDataModel{
    val vehicleTypesList = mutableListOf<VehicleTypeModel>()
    val stationList = mutableListOf<StationModel>()
    val statesList = mutableListOf<StateModel>()
    with(this) {
        vehicleTypes.map {
            vehicleTypesList.add(VehicleTypeModel(it.vehicleType, it.id))
        }
        stations.map {
            stationList.add(StationModel(it.stationName, it.id))
        }
        states.map {
            statesList.add(StateModel(it.name, it.id, it.state_code, it.zone))

        }
    }
    return AppDataModel(
        vehicleTypesList, stationList, statesList
    )
}