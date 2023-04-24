package com.example.utils

import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient {
    val baseURL = "https://api.openweathermap.org"
    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)



}