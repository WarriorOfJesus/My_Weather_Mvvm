package com.example.weekly_weather.api

import com.example.weekly_weather.api.models.FiveDaysResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FiveDaysWeatherApi {
    @GET("data/2.5/forecast")
    suspend fun getFiveDaysWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") metric: String = "metric"
    ): FiveDaysResponse
}