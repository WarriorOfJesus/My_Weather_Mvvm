package com.example.myweather.repository

import com.example.myweather.model.WeatherData

interface WeatherRepository {
    fun getWeatherData(city: String, key: String): WeatherData?
}