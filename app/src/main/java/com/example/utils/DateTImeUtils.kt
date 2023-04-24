package com.example.utils

import java.text.SimpleDateFormat

fun getFormattedDate(date: Long):String{
    val simpleDateFormat = SimpleDateFormat("dd MMMM, HH:mm")
    return simpleDateFormat.format(date)
}
