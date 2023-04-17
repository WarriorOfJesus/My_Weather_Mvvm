package com.example.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.common.mvp.BaseFragment
import com.example.myweather.R
import com.example.myweather.databinding.FragmentDetailsWeatherBinding
import com.example.myweather.model.WeatherData
import com.example.utils.Arguments
import com.example.utils.replace
import com.example.utils.viewbinding.viewBinding
import com.example.weekly_weather.ui.FiveDaysFragment
import kotlin.math.roundToInt

class DetailsWeatherFragment : BaseFragment(R.layout.fragment_details_weather) {
    companion object {
        fun newInstance(weatherData: WeatherData?) = DetailsWeatherFragment().apply {
            arguments = bundleOf(Arguments.WEATHER_DATA_KEY to weatherData)
        }
    }

    private val binding: FragmentDetailsWeatherBinding by viewBinding()

    private val weatherData: WeatherData? by lazy {
        arguments?.getParcelable(Arguments.WEATHER_DATA_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            buttonInfoFor5days.setOnClickListener {
                weatherData?.let {
                    replace(FiveDaysFragment.newInstance(it.name))
                }
            }
        }
        weatherData?.let { showWeatherData(it) }
    }

    private fun showWeatherData(data: WeatherData) {
        with(binding) {
            data.name.also { cityName2.text = it }
            "${data.main.temp.roundToInt()} °C".also { degrees2.text = it }
            "${data.wind.speed}  м/с".also { speedOfWindText.text = it }
            "${data.clouds.all}  %".also { cloudiness.text = it }
            "${data.main.pressure} гПа".also { pressureText.text = it }
            "${data.main.humidity} %".also { humidityText.text = it }
            "${data.visibility} KM".also { visibilityText.text = it }
            "${data.wind.speed} KM".also { speedOfWindText2.text = it }
            description2.text = if (data.weather.isNotEmpty())
                data.weather.first().description
            else print("sorry").toString()
        }
    }
}