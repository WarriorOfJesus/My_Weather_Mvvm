package com.example.weekly_weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.weekly_weather.model.FiveDays
import com.example.weekly_weather.model.FiveDaysWeather

class FiveDaysWeatherAdapter : RecyclerView.Adapter<FiveDaysWeatherViewHolder>() {
    private val data = mutableListOf<FiveDaysWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDaysWeatherViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_weather_details, parent, false)
        return FiveDaysWeatherViewHolder(parent)
    }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: FiveDaysWeatherViewHolder, position: Int) {
        val listItem = data[position]
        holder.onBind(listItem)
        holder.itemView.setOnClickListener {
            val description = it.tag as? String
            description?.let {
                Toast.makeText(holder.itemView.context, description, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setData(items: List<FiveDaysWeather>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}