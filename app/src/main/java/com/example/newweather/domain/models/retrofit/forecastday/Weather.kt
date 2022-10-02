package com.example.newweather.domain.models.retrofit.forecastday

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)