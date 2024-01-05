package com.freddev.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.freddev.weatherapp.data.db.entity.CURRENT_WEATHER_ID
import com.freddev.weatherapp.data.db.entity.CurrentWeatherEntry
import com.freddev.weatherapp.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    //Update and/or set
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query(value = "select * from current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query(value = "select * from current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<MetricCurrentWeatherEntry>
}