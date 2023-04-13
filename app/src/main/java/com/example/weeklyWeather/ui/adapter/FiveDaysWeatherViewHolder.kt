package com.example.weeklyWeather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.databinding.ItemWeatherDetailsBinding
import com.example.weeklyWeather.model.FiveDays

class FiveDaysWeatherViewHolder(
    private val binding: ItemWeatherDetailsBinding
) : RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        ItemWeatherDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun onBind(item : FiveDays){
        with(binding){

        }
    }
}