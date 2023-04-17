package com.example.weekly_weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    val h3 : Double
):Parcelable