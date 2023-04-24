package com.example.common.di

import com.example.utils.RetrofitClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule : InjectionModule {
    override fun create() = module {
        single {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RetrofitClient.baseURL)
                .client(client)
                .build()
        }
    }
}