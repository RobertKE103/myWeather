package com.example.newweather.domain.usecase

import com.example.newweather.domain.WeatherRepository
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response

class GetWeatherForecastDayUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(nameCity: String): Response<Weather> {
        return repository.getWeatherForecastDayRepository(nameCity)
    }
}