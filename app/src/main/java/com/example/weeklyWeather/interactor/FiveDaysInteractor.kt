package com.example.weeklyWeather.interactor

import com.example.weeklyWeather.model.FiveDays
import com.example.weeklyWeather.repository.FiveDaysRemoteRepository

class FiveDaysInteractor(
    private val remoteRepository: FiveDaysRemoteRepository
) {
    fun getFiveDaysWeatherData(cityName: String, key:String) : FiveDays {
        return remoteRepository.getFiveDaysWeatherData(cityName, key)
    }
}