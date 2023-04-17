package com.example.weekly_weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.databinding.ItemWeatherDetailsBinding
import com.example.weekly_weather.model.FiveDays
import com.example.weekly_weather.model.FiveDaysWeather

class FiveDaysWeatherViewHolder(
    private val binding: ItemWeatherDetailsBinding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        ItemWeatherDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun onBind(item : FiveDaysWeather){
        with(binding){

        }
    }
}