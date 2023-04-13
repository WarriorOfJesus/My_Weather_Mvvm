package com.example.myweather.api.models

data class WeatherDataResponse(
    val coord: CoordResponse,
    val weather: List<WeatherResponse>,
    val base: String,
    val main: MainResponse,
    val visibility: Int,
    val wind: WindResponse,
    val clouds: CloudsResponse,
    val dt: Long,
    val sys: SysResponse,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)
