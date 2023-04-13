package com.example.weeklyWeather.repository

import com.example.weeklyWeather.model.FiveDays

interface FiveDaysRepository {
    fun getFiveDaysWeatherData(cityName:String, key:String ): FiveDays?
}