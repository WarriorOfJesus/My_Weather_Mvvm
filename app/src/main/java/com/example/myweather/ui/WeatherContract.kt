package com.example.myweather.ui

import com.example.myweather.common.mvp.BaseFragmentContract
import com.example.myweather.common.mvp.MvpPresenter
import com.example.myweather.common.mvp.MvpView
import com.example.myweather.model.WeatherData

interface WeatherContract: BaseFragmentContract {
    interface View : MvpView {
        fun showWeatherData(data: WeatherData)
        fun showFailure(t: Throwable)
        fun showInfo()
        fun showLoading(isLoading: Boolean)
//        fun isVisible(isVisible:Boolean)
    }

    interface Presenter: MvpPresenter<View> {
        fun getDataFromApi(cityName: String, key: String)
    }
}