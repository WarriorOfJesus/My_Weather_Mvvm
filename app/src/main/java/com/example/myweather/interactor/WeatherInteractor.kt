package com.example.myweather.interactor

import com.example.myweather.model.WeatherData
import com.example.myweather.repository.WeatherRemoteRepository

class WeatherInteractor(
    private val remoteRepository: WeatherRemoteRepository
) {
    fun getWeatherData(city: String, key: String): WeatherData? {
        return remoteRepository.getWeatherData(city,key)
    }
}