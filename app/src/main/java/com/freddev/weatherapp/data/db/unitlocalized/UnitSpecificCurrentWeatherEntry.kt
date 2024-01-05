package com.freddev.weatherapp.data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
/*    val weatherDescriptions: List<String>
    val iconUrl: List<String>*/
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}