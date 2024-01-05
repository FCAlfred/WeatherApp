package com.freddev.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import com.freddev.weatherapp.data.repository.WeatherRepository
import com.freddev.weatherapp.utils.UnitSystem
import com.freddev.weatherapp.utils.lazyDeferred

class CurrentWeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC
    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        weatherRepository.getCurrentWeather(isMetric)
    }
}