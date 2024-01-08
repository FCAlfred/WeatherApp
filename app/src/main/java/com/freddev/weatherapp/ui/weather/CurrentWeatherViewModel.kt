package com.freddev.weatherapp.ui.weather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
    private val city: String,
    private val context: Context
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

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean>
        get() = _isConnected


    init {
        checkNetworkConnectivity()
    }

    private fun checkNetworkConnectivity() {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            val isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
            _isConnected.postValue(isConnected)

            if (isConnected) {
                viewModelScope.launch {
                    weatherNetworkDataSource.fetchCurrentWeather(city)
                }
                weatherNetworkDataSource.downloadedCurrentWeather.observeForever(
                    downloadedCurrentWeatherObserver
                )
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            val isConnected = networkInfo?.isConnected == true
            _isConnected.postValue(isConnected)

            if (isConnected) {
                viewModelScope.launch {
                    weatherNetworkDataSource.fetchCurrentWeather(city)
                }
                weatherNetworkDataSource.downloadedCurrentWeather.observeForever(
                    downloadedCurrentWeatherObserver
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        weatherNetworkDataSource.downloadedCurrentWeather.removeObserver(
            downloadedCurrentWeatherObserver
        )
    }
}
