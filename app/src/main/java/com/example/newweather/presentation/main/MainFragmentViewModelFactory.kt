package com.example.newweather.presentation.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newweather.data.WeatherRepositoryImpl
import com.example.newweather.domain.usecase.GetWeatherForecastDayUseCase
import com.example.newweather.domain.usecase.LoadDataUseCase

class MainFragmentViewModelFactory(private val context: Application): ViewModelProvider.Factory {

    private val repository = WeatherRepositoryImpl(context)
    private val getWeather = GetWeatherForecastDayUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(application = context, getWeather) as T
    }
}