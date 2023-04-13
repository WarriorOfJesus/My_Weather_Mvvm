package com.example.weeklyWeather.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.weeklyWeather.model.FiveDays

class FiveDaysWeatherAdapter : RecyclerView.Adapter<FiveDaysWeatherViewHolder>() {
    private val data = mutableListOf<FiveDays>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            R.layout.item_weather_details -> FiveDaysWeatherViewHolder(parent)
            else -> throw java.lang.IllegalArgumentException("Unknown view type")
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FiveDaysWeatherViewHolder, position: Int) {
        val listItem = data[position]
        holder.onBind(listItem)
    }
    fun setData(items: List<FiveDays>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}