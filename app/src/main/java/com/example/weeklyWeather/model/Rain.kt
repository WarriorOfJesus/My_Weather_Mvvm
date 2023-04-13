package com.example.weeklyWeather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    val h3 : Double
):Parcelable