package com.example.weekly_weather.api.models

import com.google.gson.annotations.SerializedName

data class RainResponse(
    @SerializedName("3h")
    val h3: Double
)