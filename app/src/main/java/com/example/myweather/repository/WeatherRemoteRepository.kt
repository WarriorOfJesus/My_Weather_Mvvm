package com.example.myweather.repository

import android.util.Log
import com.example.myweather.api.WeatherApi
import com.example.myweather.api.models.WeatherDataResponse
import com.example.myweather.model.WeatherConverter
import com.example.myweather.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeatherRemoteRepository(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(city: String, key: String): WeatherData {
        val response = api.getWeatherData(city, key)
        return WeatherConverter.fromNetwork(response)
    }
}