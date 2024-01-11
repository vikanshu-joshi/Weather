package com.vikanshu.data.repository

import com.vikanshu.data.local.entity.Location
import com.vikanshu.data.resource.CommunicationResult
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun searchLocation(query: String): Flow<CommunicationResult<List<Location>>>

    suspend fun getSavedLocations(): List<Location>

    suspend fun saveLocation(location: Location): Location

    suspend fun removeSavedLocation(location: Location): Location

}