package com.example.weeklyWeather.repository

import android.util.Log
import com.example.weeklyWeather.api.FiveDaysWeatherApi
import com.example.weeklyWeather.api.models.FiveDaysResponse
import com.example.weeklyWeather.model.FiveDays
import com.example.weeklyWeather.model.WeatherWeeklyConverter
import retrofit2.Call
import retrofit2.Response

class FiveDaysRemoteRepository(private val api: FiveDaysWeatherApi) : FiveDaysRepository {
    private var fiveDays: FiveDays? = null
    override fun getFiveDaysWeatherData(cityName: String, key: String): FiveDays {
        api.getFiveDaysWeatherData(cityName, key).enqueue(object :
            retrofit2.Callback<FiveDaysResponse> {
            override fun onResponse(
                call: Call<FiveDaysResponse>,
                response: Response<FiveDaysResponse>
            ) {
                if (response.isSuccessful && response.body() != null){
                    fiveDays = WeatherWeeklyConverter.fromNetwork(response.body()!!)
                }else
                    fiveDays = null
            }

            override fun onFailure(call: Call<FiveDaysResponse>, t: Throwable) {
                Log.e("FiveDays", "onResponse: ${t.message}", t)
            }

        })
        return fiveDays!!
    }

}

