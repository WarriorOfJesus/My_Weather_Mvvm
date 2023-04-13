package com.example.weeklyWeather.api

import com.example.weeklyWeather.api.models.FiveDaysResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FiveDaysWeatherApi {
    @GET("data/2.5/forecast")
    fun getFiveDaysWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): retrofit2.Call<FiveDaysResponse>
}