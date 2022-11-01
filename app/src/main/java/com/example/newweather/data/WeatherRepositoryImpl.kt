package com.example.newweather.data

import android.app.Application
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.newweather.data.database.AppDatabase
import com.example.newweather.data.network.RetrofitRepository
import com.example.newweather.data.workers.DataWorker
import com.example.newweather.domain.WeatherRepository
import com.example.newweather.domain.models.NameCitiesDbModel
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response

class WeatherRepositoryImpl(
    private val application: Application
): WeatherRepository {


    private val getWeatherRepository = RetrofitRepository()
    private val weatherDao = AppDatabase.getInstance(application).weatherDao()

    override suspend fun getWeatherForecastDayRepository(nameCity: String): Response<Weather> {
        return getWeatherRepository.requestWeather(nameCity)
    }

    override suspend fun addCity(city: String) {
        weatherDao.addBusinessItem(NameCitiesDbModel(0, city))
    }

    override suspend fun getCity(): NameCitiesDbModel {
        return weatherDao.getNameCity()
    }

    override fun startDb(){
        AppDatabase.getInstance(application)
    }

    override suspend fun delete() {
        weatherDao.deleteBusinessItem()
    }

    override fun loadData(){
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(
            DataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            DataWorker.makeRequests()
        )
    }


}