package com.example.newweather.domain.usecase

import com.example.newweather.domain.WeatherRepository
import com.example.newweather.domain.models.NameCitiesDbModel

class DeleteCityUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun deleteCity(item: NameCitiesDbModel){
        weatherRepository.delete(item)
    }

}