package com.example.weekly_weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FiveDays(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list : List<FiveDaysWeather>,
    val city:City
):Parcelable
