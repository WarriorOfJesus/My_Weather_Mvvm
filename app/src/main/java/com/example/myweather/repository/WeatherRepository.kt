package com.example.myweather.repository

import com.example.myweather.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherData(city: String, key: String): WeatherData?
}