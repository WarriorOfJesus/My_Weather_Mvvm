package com.example.weekly_weather.interactor

import com.example.weekly_weather.repository.FiveDaysRemoteRepository

class FiveDaysInteractor(
    private val remoteRepository: FiveDaysRemoteRepository
) {
    suspend fun getFiveDaysWeatherData(cityName: String, key: String) =
        remoteRepository.getFiveDaysWeatherData(cityName, key)
}