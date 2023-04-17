package com.example.myweather.ui

import com.example.common.mvp.BasePresenter
import com.example.myweather.interactor.WeatherInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class WeatherPresenter(
    private val interactor: WeatherInteractor
) : BasePresenter<WeatherContract.View>(), WeatherContract.Presenter {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun getDataFromApi(cityName: String, key: String) {
        view?.showLoading(true)
        if (cityName.length >= 3) {
            coroutineScope.launch {
                try {
                    val weather = interactor.getWeatherData(cityName, key)
                    view?.showWeatherData(weather)
                    view?.showInfo()
                } catch (t: Throwable) {
                    Timber.d("error get weather---->>>> ${t.message}")
                    view?.showErrorMessage(t)
                } finally {
                    view?.showLoading(false)
                }
            }
        }
    }

}
