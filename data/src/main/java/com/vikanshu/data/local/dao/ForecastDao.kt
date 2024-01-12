package com.vikanshu.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vikanshu.data.local.entity.Forecast

@Dao
interface ForecastDao {

    // C
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecast: Forecast)

    // R
    @Query("SELECT * FROM Forecast")
    suspend fun getAllSavedForecast(): List<Forecast>

    @Query("SELECT * FROM Forecast WHERE id=:name LIMIT 1")

    suspend fun getSavedForecastByName(name: String): Forecast?
    @Query("SELECT * FROM Forecast WHERE id IN (:name) LIMIT 1")
    suspend fun getSavedForecastByNames(name: List<String>): List<Forecast>

    // U
    @Update
    suspend fun updateForecast(forecast: Forecast)

    // D
    @Delete
    suspend fun deleteForecast(forecast: Forecast)

    @Query("DELETE FROM Forecast")
    suspend fun deleteAll()
    
}