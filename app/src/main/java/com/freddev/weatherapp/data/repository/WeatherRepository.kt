package com.freddev.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.freddev.weatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface WeatherRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}