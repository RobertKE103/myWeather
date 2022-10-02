package com.example.newweather.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.newweather.data.network.RetrofitRepository
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response

class DataWorker(context: Context, workerParameters: WorkerParameters):
    CoroutineWorker(context, workerParameters) {

    private val getWeatherRepository = RetrofitRepository()

    override suspend fun doWork(): Result {
        while (true){
            getResult(getWeatherRepository.requestWeather("chelyabinsk"))
        }
    }

    private fun getResult(data: Response<Weather>): Response<Weather> = data

    companion object {

        const val NAME = "dataWorker"

        fun makeRequests(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<DataWorker>().build()
        }
    }
}