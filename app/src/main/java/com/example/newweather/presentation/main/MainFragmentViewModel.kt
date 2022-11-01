package com.example.newweather.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import com.example.newweather.domain.usecase.GetCityFromUserUseCase
import com.example.newweather.domain.usecase.GetWeatherForecastDayUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(
    application: Application,
    private val getWeather: GetWeatherForecastDayUseCase,
    private val getCityFromUserUseCase: GetCityFromUserUseCase
) : AndroidViewModel(application) {

    private var _weather = MutableLiveData<Response<Weather>?>()
    val weather get() = _weather
    private var city = "Челябинск"


    fun requestWeather() {
        viewModelScope.launch {
            _weather.value = getWeather.invoke(city)
        }
    }

    fun getNameCity(){
        viewModelScope.launch {
            city = getCityFromUserUseCase.getCityFromUser().name
        }
    }

}