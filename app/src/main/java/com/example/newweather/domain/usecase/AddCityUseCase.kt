package com.example.newweather.domain.usecase

import com.example.newweather.domain.WeatherRepository

class AddCityUseCase(private val weatherRepository: WeatherRepository){

    suspend fun addCity(city: String){
        return weatherRepository.addCity(city)
    }

}