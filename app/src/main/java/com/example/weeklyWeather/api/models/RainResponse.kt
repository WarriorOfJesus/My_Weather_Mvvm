package com.example.weeklyWeather.api.models

import com.google.gson.annotations.SerializedName

data class RainResponse(
    @SerializedName("3h")
    val h3: Double
)