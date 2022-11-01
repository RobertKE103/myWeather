package com.example.newweather.domain.models.retrofit.forecastday

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
): Parcelable  {
    companion object : Parceler<Weather>{
        override fun create(parcel: Parcel): Weather {
            return create(parcel)
        }

        override fun Weather.write(parcel: Parcel, flags: Int) {

        }

    }
}

