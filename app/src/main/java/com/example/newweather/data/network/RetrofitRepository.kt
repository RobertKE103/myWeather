package com.example.newweather.data.network

import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response

class RetrofitRepository {

    suspend fun requestWeather(nameCity: String): Response<Weather> {
        return RetrofitInstance.api.getWeather(
            API_URL,
            nameCity,
            10,
            "no",
            "no"
        )
    }


    companion object {
        const val API_URL = "f79a084cbb1c454db02122958222009"
    }
}