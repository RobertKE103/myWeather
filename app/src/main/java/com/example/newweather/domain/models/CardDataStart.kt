package com.example.newweather.domain.models


data class CardDataStart(
    val id: Int,
    val city: String,
    val temp: String,
    var state: Boolean,
    val imgUrl: String
)
