package com.example.weekly_weather.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.doOnDetach
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.mvp.BaseMvpFragment
import com.example.details_of_day.FragmentForAdditionalInfo
import com.example.myweather.R
import com.example.myweather.databinding.FragmentFiveDaysBinding
import com.example.myweather.model.WeatherData
import com.example.utils.Arguments
import com.example.utils.replace
import com.example.utils.viewbinding.viewBinding
import com.example.weekly_weather.model.FiveDaysWeather
import com.example.weekly_weather.ui.adapter.FiveDaysWeatherAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FiveDaysFragment :
    BaseMvpFragment<FiveDaysContract.View, FiveDaysContract.Presenter>(R.layout.fragment_five_days),
    FiveDaysContract.View {

    override val presenter: FiveDaysPresenter by viewModel()

    companion object {
        fun newInstance(cityName: String?) = FiveDaysFragment().apply {
            arguments = bundleOf(Arguments.CITY_NAME to cityName)
        }
    }

    private val key: String? by lazy {
        getString(R.string.key)
    }


    private val binding: FragmentFiveDaysBinding by viewBinding()

    private val weatherData: WeatherData? by lazy {
        arguments?.getParcelable(Arguments.WEATHER_DATA_KEY)
    }

    private val cityName: String? by lazy {
        arguments?.getString(Arguments.CITY_NAME)
    }
    private val adapter: FiveDaysWeatherAdapter by lazy {
        FiveDaysWeatherAdapter { item ->
            replace(FragmentForAdditionalInfo.newInstance(item))
        }
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            cityNameRecyclerView.text = cityName


            recyclerView.adapter = adapter
            recyclerView.layoutManager = linearLayoutManager

            buttonBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        presenter.getWeatherData(cityName.toString(), key.toString())

        binding.recyclerView.doOnDetach {
            binding.recyclerView.layoutManager = null
        }

    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    override fun showData(data: List<FiveDaysWeather>) {
        adapter.setData(data)
    }


    override fun showErrorMessage(t: Throwable) {
        Timber.e(t)
    }

    override fun showErrorMessage(messageRes: Int) {
        Timber.e(getString(messageRes))
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}