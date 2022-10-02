package com.example.newweather.data

import android.app.Application
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.newweather.data.database.AppDatabase
import com.example.newweather.data.network.RetrofitRepository
import com.example.newweather.data.workers.DataWorker
import com.example.newweather.domain.WeatherRepository
import com.example.newweather.domain.models.NameCitiesDbModel
import com.example.newweather.domain.models.retrofit.forecastday.Weather
import retrofit2.Response
import java.lang.ref.Reference

class WeatherRepositoryImpl(
    private val application: Application
): WeatherRepository {


    private val getWeatherRepository = RetrofitRepository()
    private val weatherDao = AppDatabase.getInstance(application).weatherDao()
    private var wList: List<NameCitiesDbModel> = mutableListOf()

    override suspend fun getWeatherForecastDayRepository(nameCity: String): Response<Weather> {
        return getWeatherRepository.requestWeather(nameCity)
    }

    override suspend fun addCity(city: String) {
        val dbModel = NameCitiesDbModel(id = 0, city, "0", false, "")
        weatherDao.addBusinessItem(dbModel)
    }

    override suspend fun getCity() {
        wList = weatherDao.getNameCity()
    }

    override suspend fun delete(nameCitiesDbModel: NameCitiesDbModel) {
        weatherDao.deleteBusinessItem(nameCitiesDbModel.id)
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