package com.example.newweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newweather.domain.models.NameCitiesDbModel

@Dao
interface WeatherDao {

    // сделал возврощаемым List место LiveData и добавил suspend
    @Query("SELECT * FROM weatherNameTable")
    suspend fun getNameCity(): List<NameCitiesDbModel>

    @Query("DELETE FROM weatherNameTable WHERE id=:weatherItemId")
    suspend fun deleteBusinessItem(weatherItemId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBusinessItem(weatherName: NameCitiesDbModel)

}