package com.example.weeklyWeather.model

import android.os.Parcelable
import com.example.myweather.model.Clouds
import com.example.myweather.model.Weather
import com.example.myweather.model.Wind
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FiveDaysWeather(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val dt_txt: String
):Parcelable
