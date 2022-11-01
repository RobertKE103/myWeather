package com.example.newweather.data.database

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newweather.domain.models.NameCitiesDbModel

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weatherNameTable")
    suspend fun getNameCity(): NameCitiesDbModel

    @Query("DELETE FROM weatherNameTable")
    suspend fun deleteBusinessItem()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBusinessItem(city: NameCitiesDbModel)

}