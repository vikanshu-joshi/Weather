package com.vikanshu.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vikanshu.data.local.entity.CurrentWeather

@Dao
interface CurrentWeatherDao {

    // C

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: CurrentWeather)

    // R

    @Query("SELECT * FROM CurrentWeather")
    suspend fun getAllSavedWeather(): List<CurrentWeather>

    @Query("SELECT * FROM CurrentWeather where id=:name LIMIT 1")
    suspend fun getSavedWeatherByName(name: String): CurrentWeather

    // U

    @Update
    suspend fun updateWeather(weather: CurrentWeather)

    // D

    @Delete
    suspend fun deleteWeather(weather: CurrentWeather)

    @Query("DELETE FROM CurrentWeather")
    suspend fun deleteAll()

}