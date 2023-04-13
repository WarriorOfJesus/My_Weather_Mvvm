package com.example.weeklyWeather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myweather.R
import com.example.common.mvp.BaseFragment
import com.example.common.mvp.BaseMvpFragment
import com.example.myweather.api.RetrofitClient
import com.example.myweather.api.WeatherApi
import com.example.myweather.databinding.FragmentWeather2Binding
import com.example.myweather.databinding.FragmentWeatherBinding
import com.example.myweather.interactor.WeatherInteractor
import com.example.myweather.main_page.ui.KelvinConverter
import com.example.myweather.repository.WeatherRemoteRepository
import com.example.myweather.ui.WeatherFragment
import com.example.myweather.ui.WeatherPresenter
import com.example.myweather.utils.changeFragment
import com.example.weeklyWeather.api.FiveDaysWeatherApi
import com.example.weeklyWeather.interactor.FiveDaysInteractor
import com.example.weeklyWeather.model.FiveDaysWeather
import com.example.weeklyWeather.repository.FiveDaysRemoteRepository

class FragmentWeather2 :
    BaseMvpFragment<FiveDaysContract.View, FiveDaysContract.Presenter>
        (R.layout.fragment_weather2), FiveDaysContract.View {
    private val key: String by lazy {
        getString(R.string.key)
    }
    private val api: FiveDaysWeatherApi =
        RetrofitClient.getClient("https://api.openweathermap.org")
            .create(FiveDaysWeatherApi::class.java)

    private val remoteRepository: FiveDaysRemoteRepository = FiveDaysRemoteRepository(api)

    private val interactor: FiveDaysInteractor = FiveDaysInteractor(remoteRepository)
    override val presenter: FiveDaysPresenter = FiveDaysPresenter(interactor = interactor)
    private val kelvinConverter = KelvinConverter()

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
        with(binding){
            buttonBack.setOnClickListener {
                val fragment = WeatherFragment()
                changeFragment(fragment, R.id.fragmentContainer)
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
        TODO("Not yet implemented")
    }

    override fun showFiveDaysData(data: List<FiveDaysWeather>) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(t: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(messageRes: Int) {
        TODO("Not yet implemented")
    }


}