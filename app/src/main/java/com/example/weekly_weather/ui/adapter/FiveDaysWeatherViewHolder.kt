package com.example.weekly_weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.databinding.ItemWeatherDetailsBinding
import com.example.weekly_weather.model.FiveDaysWeather
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class FiveDaysWeatherViewHolder(
    private val binding: ItemWeatherDetailsBinding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        ItemWeatherDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun onBind(item: FiveDaysWeather) {
        val simpleDateFormat = SimpleDateFormat("dd MMMM, HH:mm")
        val data = item.dt
        val ts = simpleDateFormat.format(data.times(1000L))
        with(binding) {
            dataDay.text = ts
            "${item.main.temp.roundToInt()} °C".also { degreesForFiveDays.text = it }
            "${item.main.pressure} гПа".also { pressureText.text = it }
            "${item.clouds.all}  %".also { cloudCoverText.text = it }
            "${item.main.humidity} %".also { humidityText.text = it }
        }
    }
}