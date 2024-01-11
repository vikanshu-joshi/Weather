package com.vikanshu.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vikanshu.data.local.entity.Location

@Dao
interface LocationDao {

    // C

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)


    // R

    @Query("SELECT * FROM Location")
    suspend fun getAllSavedLocations(): List<Location>

    @Query("SELECT * FROM Location where name=:name LIMIT 1")
    suspend fun getSavedLocationByName(name: String): Location

    // U

    @Update
    suspend fun updateLocation(location: Location)

    // D

    @Delete
    suspend fun deleteLocation(location: Location)

    @Query("DELETE FROM Location")
    suspend fun deleteAll()

}