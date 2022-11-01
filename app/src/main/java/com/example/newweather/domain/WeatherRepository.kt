package com.example.newweather.domain

import com.example.newweather.domain.models.NameCitiesDbModel
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response
import java.util.jar.Attributes.Name

interface WeatherRepository {

    suspend fun getWeatherForecastDayRepository(nameCity: String): Response<Weather>

    suspend fun addCity(city: String)

    suspend fun getCity(): NameCitiesDbModel

    suspend fun delete()

    fun startDb()

    fun loadData()

}