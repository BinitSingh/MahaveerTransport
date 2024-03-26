package com.mobile.mahaveertransport.data.repository


import com.mobile.mahaveertransport.data.datamapper.map
import com.mobile.mahaveertransport.domain.datamodel.Result
import com.mobile.mahaveertransport.data.webservice.IDataSource
import com.mobile.mahaveertransport.domain.datamodel.AppDataModel
import com.mobile.mahaveertransport.domain.datamodel.LoginModel
import com.mobile.mahaveertransport.domain.datamodel.Movie
import com.mobile.mahaveertransport.domain.datamodel.StateModel
import com.mobile.mahaveertransport.domain.datamodel.StationModel
import com.mobile.mahaveertransport.domain.datamodel.VehicleTypeModel
import com.mobile.mahaveertransport.domain.repository.IMovieRepository
import com.mobile.mahaveertransport.domain.request.AuthenticationRequest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiCall: IDataSource
    ) : IMovieRepository {

    private val vehicleTypes = mutableListOf<VehicleTypeModel>()
    private val stations = mutableListOf<StationModel>()
    private val states = mutableListOf<StateModel>()

    override suspend fun getMovieList(): Flow<Result<List<Movie>>> {
        return apiCall.getMovieList().map {
            when (it) {
                is Result.Success -> {
                    Result.Success(it.data.map())
                }
                is Result.Failure -> {
                    it
                }
            }
        }
    }

    override suspend fun authenticate(request: AuthenticationRequest): Flow<Result<LoginModel>> {
        return apiCall.authenticate(request).map {
            when(it){
                is Result.Success -> {
                    Result.Success(it.data.map())
                }
                is Result.Failure -> {
                    it
                }
            }
        }
    }

    override suspend fun getAppData(): Flow<Result<AppDataModel>> {
        return apiCall.getAppData().map {
            when (it) {
                is Result.Success -> {
                    val appData = it.data.map()
                    vehicleTypes.addAll(appData.vehicleTypes)
                    stations.addAll(appData.stations)
                    states.addAll(appData.states)
                    Result.Success(appData)
                }

                is Result.Failure -> {
                    it
                }
            }
        }
    }
}