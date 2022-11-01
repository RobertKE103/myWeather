package com.example.newweather.domain.usecase

import com.example.newweather.domain.WeatherRepository

class DeleteCityUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun deleteCity() {
        weatherRepository.delete()
    }

}