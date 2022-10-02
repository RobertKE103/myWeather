package com.example.newweather.data.network

import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") key: String ,
        @Query("q") query: String,
        @Query("days") days: Int = 10,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Response<Weather>



}