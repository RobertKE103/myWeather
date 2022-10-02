package com.example.newweather.domain.usecase

import com.example.newweather.domain.WeatherRepository

class GetCityFromUserUseCase(private val repository: WeatherRepository) {

    suspend fun getCityFromUser() {
        return repository.getCity()
    }

}