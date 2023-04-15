package com.example.weeklyWeather.ui

import com.example.common.mvp.MvpPresenter
import com.example.common.mvp.MvpView
import com.example.myweather.model.WeatherData
import com.example.weeklyWeather.model.FiveDays
import com.example.weeklyWeather.model.FiveDaysWeather

interface FiveDaysContract {
    interface View : MvpView {
        fun showLoading(isLoading: Boolean)
        fun showError(error: String)
        fun showFiveDaysData(data: List<FiveDaysWeather>)
        fun showWeatherData(data : WeatherData)
    }

    interface Presenter : MvpPresenter<View> {
        fun getWeatherData(city: String, key: String)
    }
}