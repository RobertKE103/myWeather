package com.example.newweather.presentation.startWork

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newweather.data.WeatherRepositoryImpl
import com.example.newweather.domain.models.NameCitiesDbModel
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import com.example.newweather.domain.usecase.AddCityUseCase
import com.example.newweather.domain.usecase.DeleteCityUseCase
import com.example.newweather.domain.usecase.GetCityFromUserUseCase
import com.example.newweather.domain.usecase.GetWeatherForecastDayUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherRepositoryImpl(application)
    private val addCityUseCase = AddCityUseCase(repository)
    private val getCityFromUserUseCase = GetCityFromUserUseCase(repository)
    private val deleteCityUseCase = DeleteCityUseCase(repository)
    private var _weather = MutableLiveData<Response<Weather>?>()
    private val getWeather = GetWeatherForecastDayUseCase(repository)
//    private var _weatherList = MutableLiveData<List<NameCitiesDbModel>>()
//    val weatherList: LiveData<List<NameCitiesDbModel>> get() = _weatherList
    val weather get() = _weather


    fun addCity(city: String){
        viewModelScope.launch {
            addCityUseCase.addCity(city)
        }
    }

    fun delete(nameCitiesDbModel: NameCitiesDbModel){
        viewModelScope.launch {
            deleteCityUseCase.deleteCity(nameCitiesDbModel)
        }
    }

    fun requests(city: String) {
        viewModelScope.launch {
            _weather.value = getWeather.invoke(city)
        }
    }


}