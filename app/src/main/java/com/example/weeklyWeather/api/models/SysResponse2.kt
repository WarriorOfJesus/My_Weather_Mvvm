package com.example.weeklyWeather.api.models

import com.google.gson.annotations.SerializedName

data class SysResponse2(
    @SerializedName("pod")
    val pod : String
    )