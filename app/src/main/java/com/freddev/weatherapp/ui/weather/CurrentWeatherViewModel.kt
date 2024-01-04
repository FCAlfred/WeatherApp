package com.freddev.weatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrentWeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is current weather Fragment"
    }
    val text: LiveData<String> = _text
}