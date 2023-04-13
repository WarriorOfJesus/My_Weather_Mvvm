package com.example.weeklyWeather.model

import android.os.Parcelable
import com.example.myweather.model.Coord
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val id: Int,
    val name:String,
    val coord: Coord,
    val country:String,
    val population:Int,
    val timeZone: Int,
    val sunrise: Long,
    val sunset: Long
):Parcelable