package com.example.weeklyWeather.ui

import com.example.common.mvp.BasePresenter
import com.example.weeklyWeather.interactor.FiveDaysInteractor
import timber.log.Timber

class FiveDaysPresenter(
    private val interactor: FiveDaysInteractor
) : BasePresenter<FiveDaysContract.View>(), FiveDaysContract.Presenter {
    override fun getWeatherData(city: String, key: String) {
        view?.showLoading(isLoading = true)
        if (city.length >= 3) {
            try {
                view?.showLoading(isLoading = true)
                val data = interactor.getFiveDaysWeatherData(city, key)
                val list = data.list
                view?.showFiveDaysData(list)
            } catch (t: Throwable) {
                Timber.tag("Error get five days weather data ").e(t.message.toString())
                view?.showError(t.message.toString())
            } finally {
                view?.showLoading(isLoading = false)
            }

        }
    }


}
/* view?.showLoading(true)
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
    }*/