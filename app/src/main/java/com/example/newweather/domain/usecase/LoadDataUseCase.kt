package com.example.newweather.domain.usecase

import com.example.newweather.domain.WeatherRepository

class LoadDataUseCase(private val repository: WeatherRepository) {
    operator fun invoke(){
        return repository.loadData()
    }
}