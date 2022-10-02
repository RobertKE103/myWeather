package com.example.newweather.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherNameTable")
data class NameCitiesDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var temp: String,
    var state: Boolean,
    var imgUrl: String
)