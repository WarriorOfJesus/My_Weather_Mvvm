package com.example.weekly_weather.ui

import com.example.common.mvp.MvpPresenter
import com.example.common.mvp.MvpView
import com.example.myweather.model.WeatherData
import com.example.weekly_weather.model.FiveDaysWeather

interface FiveDaysContract {
    interface View : MvpView {
        fun showLoading(isLoading: Boolean)
        fun showData(data: List<FiveDaysWeather>)
    }

    interface Presenter : MvpPresenter<View> {
        fun getWeatherData(city: String, key: String)
    }
}