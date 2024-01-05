package com.freddev.weatherapp.data.network.response

import com.freddev.weatherapp.data.db.entity.CurrentWeatherEntry
import com.freddev.weatherapp.data.db.entity.Location
import com.freddev.weatherapp.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    val request: Request,
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)