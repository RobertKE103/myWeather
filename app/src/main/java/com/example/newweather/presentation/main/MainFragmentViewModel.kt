package com.example.newweather.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newweather.data.WeatherRepositoryImpl
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import com.example.newweather.domain.usecase.GetWeatherForecastDayUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(
    application: Application,
    private val getWeather: GetWeatherForecastDayUseCase
) : AndroidViewModel(application) {

    private var _weather = MutableLiveData<Response<Weather>?>()
    val weather get() = _weather


    fun requestWeather() {
        viewModelScope.launch {
            try {
                _weather.value = getWeather(getNameCity())
            } catch (_: Exception) {
            }
        }
    }


    private fun getNameCity(): String {
        return "Chelyabinsk"
    }

}