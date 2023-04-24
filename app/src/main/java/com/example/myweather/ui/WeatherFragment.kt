package com.example.myweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.common.mvp.BaseMvpFragment
import com.example.details.DetailsWeatherFragment
import com.example.myweather.R
import com.example.myweather.databinding.FragmentWeatherBinding
import com.example.myweather.model.WeatherData
import com.example.utils.replace
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.math.roundToInt

class WeatherFragment :
    BaseMvpFragment<WeatherContract.View, WeatherContract.Presenter>(R.layout.fragment_weather),
    WeatherContract.View {

    private val key: String by lazy {
        getString(R.string.key)
    }
    override val presenter: WeatherPresenter by viewModel()

    companion object {
        fun getNewInstance(args: Bundle?): WeatherFragment {
            val weatherFragment = WeatherFragment()
            weatherFragment.arguments = args
            return weatherFragment
        }
    }



    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            editCity.doAfterTextChanged {
                val cityName = editCity.text.toString()
                check(cityName)

            }
        }
    }


    override fun showWeatherData(data: WeatherData) {
        with(binding) {
            cityName.text = data.name
            "${data.main.temp.roundToInt()} °C".also { degrees.text = it }
            description.text = if (data.weather.isNotEmpty())
                data.weather.first().description
            else print("sorry").toString()

            buttonMoreInfo.setOnClickListener {
                replace(DetailsWeatherFragment.newInstance(data))
            }
        }
    }

    override fun showFailure(t: Throwable) {
        Timber.d("---->>>> ${t.message}")
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    override fun showErrorMessage(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
        Timber.d("---->>>> ${t.message}")
    }

    override fun showErrorMessage(@StringRes messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
        Timber.d("---->>>> $messageRes")
    }

    override fun showInfo() {
        with(binding) {
            cityName.isVisible = true
            degrees.isVisible = true
            description.isVisible = true
        }
    }

    private fun hideInfo() {
        with(binding) {
            progress.isVisible = false
            cityName.isVisible = false
            degrees.isVisible = false
            description.isVisible = false
        }
    }

    private fun deleteInfo() {
        with(binding) {
            cityName.text = ""
            degrees.text = ""
            description.text = ""
        }
    }

    private fun check(cityName: String) {
        if (cityName.isNotEmpty()) {
            if (cityName != "") presenter.getDataFromApi(cityName, key)
//                                showInfo()
        }
        if (cityName.isEmpty()) {
            hideInfo()
            deleteInfo()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        requireActivity().finish()
    }
}