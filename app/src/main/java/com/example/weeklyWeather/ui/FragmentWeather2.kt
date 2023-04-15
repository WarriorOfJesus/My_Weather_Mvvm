package com.example.weeklyWeather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.common.mvp.BaseMvpFragment
import com.example.myweather.R
import com.example.myweather.api.RetrofitClient
import com.example.myweather.databinding.FragmentWeather2Binding
import com.example.myweather.model.WeatherData
import com.example.myweather.ui.WeatherFragment
import com.example.myweather.utils.changeFragment
import com.example.weeklyWeather.api.FiveDaysWeatherApi
import com.example.weeklyWeather.interactor.FiveDaysInteractor
import com.example.weeklyWeather.model.FiveDays
import com.example.weeklyWeather.model.FiveDaysWeather
import com.example.weeklyWeather.repository.FiveDaysRemoteRepository
import timber.log.Timber
import kotlin.math.roundToInt

class FragmentWeather2 :
    BaseMvpFragment<FiveDaysContract.View, FiveDaysContract.Presenter>
        (R.layout.fragment_weather2), FiveDaysContract.View {
    private val key: String by lazy {
        getString(R.string.key)
    }
    private val bundle = Bundle()

    private val api: FiveDaysWeatherApi =
        RetrofitClient.getClient("https://api.openweathermap.org")
            .create(FiveDaysWeatherApi::class.java)

    private val remoteRepository: FiveDaysRemoteRepository = FiveDaysRemoteRepository(api)

    private val interactor: FiveDaysInteractor = FiveDaysInteractor(remoteRepository)
    override val presenter: FiveDaysPresenter = FiveDaysPresenter(interactor = interactor)

    private lateinit var binding: FragmentWeather2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeather2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonBack.setOnClickListener {
                val fragment = WeatherFragment()
                changeFragment(fragment, R.id.fragmentContainer)
                presenter.getWeatherData("Bishkek", key)
            }
            buttonInfoFor5days.setOnClickListener {
                val fragment = InformationFor5days()
                changeFragment(fragment, R.id.fragmentContainer)
            }
        }
    }

    override fun showLoading(isLoading: Boolean) {

    }

    override fun showError(error: String) {
        Timber.d("---->>>> $error")
    }

    override fun showFiveDaysData(data: List<FiveDaysWeather>) {

    }

    override fun showWeatherData(data: WeatherData) {

        with(binding) {
            cityName2.text = bundle.putString("cityName", data.name ).toString()
            cityName2.text = arguments.toString()
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

    override fun showErrorMessage(t: Throwable?) {
        Toast.makeText(requireContext(), t?.message, Toast.LENGTH_SHORT).show()
        Timber.d("---->>>> ${t?.message}")
    }

    override fun showErrorMessage(messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
        Timber.d("---->>>> $messageRes")
    }


}