package com.example.myweather.api.models

data class SysResponse(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
