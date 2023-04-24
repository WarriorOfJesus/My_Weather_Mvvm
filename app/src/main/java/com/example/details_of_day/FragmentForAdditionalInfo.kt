package com.example.details_of_day

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.common.mvp.BaseFragment
import com.example.myweather.R
import com.example.myweather.databinding.FragmentForAdditionalInfoBinding
import com.example.utils.Arguments
import com.example.utils.getFormattedDate
import com.example.utils.viewbinding.viewBinding
import com.example.weekly_weather.model.FiveDaysWeather
import com.example.weekly_weather.ui.FiveDaysPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class FragmentForAdditionalInfo : BaseFragment(R.layout.fragment_for_additional_info) {

    companion object {
        fun newInstance(data: FiveDaysWeather) = FragmentForAdditionalInfo().apply {
            arguments = bundleOf(Arguments.FIVE_DAYS_WEATHER to data)
        }
    }


    private val binding: FragmentForAdditionalInfoBinding by viewBinding()
    private val presenter: FiveDaysPresenter by viewModel()

    private val key: String? by lazy {
        getString(R.string.key)
    }

    private val cityName: String? by lazy {
        arguments?.getString(Arguments.CITY_NAME)
    }

    private val data: FiveDaysWeather? by lazy {
        arguments?.getParcelable(Arguments.FIVE_DAYS_WEATHER)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            data?.dt?.let {
                date.text = getFormattedDate(it.times(1000L))

            }
            buttonBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
            val strBuilder = StringBuilder()
            when (data?.wind?.deg) {
                in 0..22, in 338..360 -> strBuilder.append("С, ")
                in 23..67 -> strBuilder.append("СВ, ")
                in 68..112 -> strBuilder.append("В, ")
                in 113..157 -> strBuilder.append("ЮВ, ")
                in 158..202 -> strBuilder.append("Ю, ")
                in 203..247 -> strBuilder.append("ЮЗ, ")
                in 248..292 -> strBuilder.append("З, ")
                in 293..357 -> strBuilder.append("СЗ, ")
            }
            strBuilder.append(data?.wind?.deg).append('\u00B0')
            tvWindDirection.text = strBuilder
            "${data?.main?.temp?.roundToInt()} °C".also { degrees2.text = it }
            "${data?.wind?.speed}  м/с".also { speedOfWindText.text = it }
            "${data?.clouds?.all}  %".also { cloudiness.text = it }
            "${data?.main?.pressure} гПа".also { pressureText.text = it }
            "${data?.main?.humidity} %".also { humidityText.text = it }
            "${data?.visibility?.div(1000)} KM".also { visibilityText.text = it }
            description2.text = if (data?.weather?.isNotEmpty() == true)
                data?.weather?.first()?.description
            else print("sorry").toString()
        }
        presenter.getWeatherData(cityName.toString(), key.toString())
    }
}