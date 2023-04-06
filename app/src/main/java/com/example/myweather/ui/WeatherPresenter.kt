package com.example.myweather.ui

import android.util.Log
import android.widget.Toast
import com.example.myweather.common.mvp.BasePresenter
import com.example.myweather.interactor.WeatherInteractor

class WeatherPresenter(
    private val interactor: WeatherInteractor
) : BasePresenter<WeatherContract.View>(), WeatherContract.Presenter {

    override fun getDataFromApi(cityName: String, key: String) {
        view?.showLoading(true)
        if (cityName.length >= 3) {
            try {
                val weather = interactor.getWeatherData(cityName, key)
                if (weather != null) {
                    view?.showWeatherData(weather)
                    view?.showInfo()
                }
            } catch (t: Throwable) {
                Log.e("Error get weather data ", t.message.toString())
                view?.showErrorMessage()
            } finally {
                view?.showLoading(false)
            }
        }
    }

}
