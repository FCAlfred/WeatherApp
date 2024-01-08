package com.freddev.weatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddev.weatherapp.data.network.WeatherNetworkDataSource
import com.freddev.weatherapp.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val city: String
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeatherResponse>()
    val currentWeather: LiveData<CurrentWeatherResponse>
        get() = _currentWeather

    private val downloadedCurrentWeatherObserver =
        Observer<CurrentWeatherResponse> { currentWeatherResponse ->
            currentWeatherResponse.let {
                _currentWeather.postValue(it)
            }
        }

    init {
        viewModelScope.launch {
            weatherNetworkDataSource.fetchCurrentWeather(city)
        }
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever(
            downloadedCurrentWeatherObserver
        )
    }

    override fun onCleared() {
        super.onCleared()
        weatherNetworkDataSource.downloadedCurrentWeather.removeObserver(
            downloadedCurrentWeatherObserver
        )
    }
}
