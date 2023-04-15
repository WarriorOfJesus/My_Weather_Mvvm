package com.example.myweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import com.example.myweather.R
import com.example.myweather.api.RetrofitClient
import com.example.common.mvp.BaseMvpFragment
import com.example.myweather.databinding.FragmentWeatherBinding
import com.example.myweather.api.WeatherApi
import com.example.myweather.interactor.WeatherInteractor
import com.example.myweather.model.WeatherData
import com.example.myweather.repository.WeatherRemoteRepository
import com.example.myweather.utils.changeFragment
import com.example.weeklyWeather.ui.FragmentWeather2
import timber.log.Timber
import kotlin.math.roundToInt

class WeatherFragment :
    BaseMvpFragment<WeatherContract.View, WeatherContract.Presenter>(R.layout.fragment_weather),
    WeatherContract.View {

    private val key: String by lazy {
        getString(R.string.key)
    }

    companion object{
        fun getNewInstance(args:Bundle?):WeatherFragment{
            val weatherFragment = WeatherFragment()
            weatherFragment.arguments = args
            return weatherFragment
        }
    }

    private val api: WeatherApi =
        RetrofitClient.getClient("https://api.openweathermap.org").create(WeatherApi::class.java)

    private val remoteRepository: WeatherRemoteRepository = WeatherRemoteRepository(api)

    private val interactor: WeatherInteractor = WeatherInteractor(remoteRepository)
    override val presenter: WeatherPresenter = WeatherPresenter(interactor)


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
                if (it != null) {
                    if (it.isNotEmpty()) {
                        it.toString()
                            .let { city ->
                                if (city != "") presenter.getDataFromApi(city, key)
//                                showInfo()
                            }
                    }
                    if (it.isEmpty()) {
                        hideInfo()
                        deleteInfo()
                    }
                }
            }
            buttonMoreInfo.setOnClickListener {
                val fragment = FragmentWeather2()
                fragment.arguments?.putAll(savedInstanceState)
                changeFragment(fragment = fragment, R.id.fragmentContainer)

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
        }
    }

    override fun showFailure(t: Throwable) {
        Timber.d("---->>>> ${t.message}")
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    override fun showErrorMessage(t: Throwable?) {
        Toast.makeText(requireContext(), t?.message, Toast.LENGTH_SHORT).show()
        Timber.d("---->>>> ${t?.message}")
    }

    override fun showErrorMessage(@StringRes messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
        Timber.d("---->>>> $messageRes")
    }
//            binding.progress.isVisible = false

    fun hideInfo() {
        with(binding) {
            progress.isVisible = false
            cityName.isVisible = false
            degrees.isVisible = false
            description.isVisible = false
        }
    }

    fun deleteInfo() {
        with(binding) {
            cityName.text = ""
            degrees.text = ""
            description.text = ""
        }
    }

    override fun showInfo() {
        with(binding) {
            cityName.isVisible = true
            degrees.isVisible = true
            description.isVisible = true
        }
    }
}