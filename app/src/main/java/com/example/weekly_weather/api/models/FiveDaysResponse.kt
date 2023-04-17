package com.example.weekly_weather.api.models

import com.google.gson.annotations.SerializedName

data class FiveDaysResponse(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list : List<WeatherOneDataResponse>,
    @SerializedName("city")
    val city:CityResponse
)
