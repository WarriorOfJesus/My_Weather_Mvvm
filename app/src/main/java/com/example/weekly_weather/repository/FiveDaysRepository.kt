package com.example.weekly_weather.repository

import com.example.weekly_weather.model.FiveDays

interface FiveDaysRepository {
   suspend fun getFiveDaysWeatherData(cityName:String, key:String ): FiveDays
}