package com.example.newweather.domain.usecase

import com.example.newweather.domain.WeatherRepository
import com.example.newweather.domain.models.NameCitiesDbModel

class GetCityFromUserUseCase(private val repository: WeatherRepository) {

    suspend fun getCityFromUser(): NameCitiesDbModel {
        return repository.getCity()
    }

}