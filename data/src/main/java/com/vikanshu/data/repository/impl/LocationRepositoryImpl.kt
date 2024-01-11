package com.vikanshu.data.repository.impl

import com.vikanshu.data.api.WeatherApi
import com.vikanshu.data.local.dao.LocationDao
import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.repository.LocationRepository
import com.vikanshu.data.resource.CommunicationResult
import com.vikanshu.data.resource.apiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class LocationRepositoryImpl @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val locationDao: LocationDao,
    private val weatherApi: WeatherApi
) : LocationRepository {

    override suspend fun searchLocation(query: String): Flow<CommunicationResult<List<Location>>> {
        return flow {
            emit(CommunicationResult.Loading)
            emit(apiCall(operation = {
                weatherApi.searchLocations(query)
            }, converter = {
                it!!.map { res ->
                    Location(
                        name = res.name ?: "",
                        region = res.region ?: "",
                        country = res.country ?: "",
                        lat = res.lat ?: 0.0,
                        lon = res.lon ?: 0.0,
                    )
                }
            }, isValidResponse = {
                it != null
            }))
            return@flow
        }.flowOn(ioDispatcher)
    }

    override suspend fun getSavedLocations(): List<Location> {
        return withContext(ioDispatcher) {
            locationDao.getAllSavedLocations()
        }
    }

    override suspend fun saveLocation(location: Location): Location {
        return withContext(ioDispatcher) {
            locationDao.insertLocation(location)
            location
        }
    }

    override suspend fun removeSavedLocation(location: Location): Location {
        return withContext(ioDispatcher) {
            locationDao.deleteLocation(location)
            location
        }
    }


}