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
    private var weatherData: WeatherData? = null

    override fun getWeatherData(city: String, key: String): WeatherData? {
        api.getWeatherData(city, key).enqueue(object : Callback<WeatherDataResponse> {
            override fun onResponse(
                call: Call<WeatherDataResponse>,
                response: Response<WeatherDataResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    weatherData = WeatherConverter.fromNetwork(response.body())
                } else
                    weatherData = null
            }

            override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                Timber.e("WeatherData onResponse: " + t.message)
            }
        })
        return weatherData
    }
}