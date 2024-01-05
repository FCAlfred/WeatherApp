package com.freddev.weatherapp.ui.future_climate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FutureWeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "To Be Implemented"
    }
    val text: LiveData<String> = _text
}