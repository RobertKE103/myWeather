package com.example.newweather.presentation.startWork

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newweather.data.WeatherRepositoryImpl
import com.example.newweather.domain.models.NameCitiesDbModel
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import com.example.newweather.domain.usecase.AddCityUseCase
import com.example.newweather.domain.usecase.DeleteCityUseCase
import com.example.newweather.domain.usecase.GetCityFromUserUseCase
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.jar.Attributes.Name

class SearchFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherRepositoryImpl(application)
    private val addCityUseCase = AddCityUseCase(repository)
    private val deleteCityUseCase = DeleteCityUseCase(repository)
    private val getCityFromUserUseCase = GetCityFromUserUseCase(repository)
    private var _weather = MutableLiveData<Response<Weather>?>()
    val weather get() = _weather
    private var _city = MutableLiveData<NameCitiesDbModel>()
    val city: LiveData<NameCitiesDbModel> get() = _city


    fun addCity(city: String){
        viewModelScope.launch {
            addCityUseCase.addCity(city)
        }
    }

    fun getCity(){
        viewModelScope.launch {
            _city.value = getCityFromUserUseCase.getCityFromUser()
        }
    }


    fun deleteCity(){
        viewModelScope.launch {
            deleteCityUseCase.deleteCity()
        }
    }



}