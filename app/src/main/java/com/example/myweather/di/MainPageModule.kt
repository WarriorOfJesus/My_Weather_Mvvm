package com.example.myweather.di

import com.example.common.di.InjectionModule
import com.example.myweather.api.WeatherApi
import com.example.myweather.interactor.WeatherInteractor
import com.example.myweather.repository.WeatherRemoteRepository
import com.example.myweather.repository.WeatherRepository
import com.example.myweather.ui.WeatherPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object MainPageModule : InjectionModule {
    override fun create() = module {
        single { get<Retrofit>().create(WeatherApi::class.java) }
        single<WeatherRepository> { WeatherRemoteRepository(get()) }
        single { WeatherRemoteRepository(get()) } bind WeatherRepository::class
        factory { WeatherInteractor(get()) }

        viewModel { WeatherPresenter(get()) }
    }
}