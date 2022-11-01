package com.example.newweather.data.network

import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response

class RetrofitRepository {

    suspend fun requestWeather(nameCity: String): Response<Weather> {
        return RetrofitInstance.api.getWeather(
            API_URL,
            nameCity,
            3,
            "no",
            "no"
        )
    }


    companion object {
        const val API_URL = "e062d43e8d0a44a590a75330221509"
    }
}