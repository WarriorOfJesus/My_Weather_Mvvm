package com.example.weekly_weather.api.models

import com.example.myweather.api.models.CoordResponse
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("coord")
    val coord: CoordResponse,
    @SerializedName("country")
    val country:String,
    @SerializedName("population")
    val population:Int,
    @SerializedName("timeZone")
    val timeZone: Int,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
)