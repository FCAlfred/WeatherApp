package com.freddev.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.freddev.weatherapp.data.network.WeatherNetworkDataSource


class CurrentWeatherViewModelFactory(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val city: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(weatherNetworkDataSource, city) as T
    }
}
