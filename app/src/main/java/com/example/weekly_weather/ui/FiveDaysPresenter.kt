package com.example.weekly_weather.ui

import com.example.common.mvp.BasePresenter
import com.example.weekly_weather.interactor.FiveDaysInteractor
import kotlinx.coroutines.launch
import timber.log.Timber

class FiveDaysPresenter(
    private val interactor: FiveDaysInteractor,
) : BasePresenter<FiveDaysContract.View>(), FiveDaysContract.Presenter {

    override fun getWeatherData(city: String, key: String) {
        launch {
            view?.showLoading(isLoading = true)
            if (city.length >= 3) {
                try {
                    view?.showLoading(isLoading = true)
                    val weatherData = interactor.getFiveDaysWeatherData(city, key)
                    view?.showData(weatherData.list)
                } catch (t: Throwable) {
                    Timber.tag("Error get five days weather data ").e(t.message.toString())
                    view?.showErrorMessage(t)
                } finally {
                    view?.showLoading(isLoading = false)
                }
            }
        }
    }
}
