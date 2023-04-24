package com.example.weekly_weather.di

import com.example.common.di.InjectionModule
import com.example.weekly_weather.api.FiveDaysWeatherApi
import com.example.weekly_weather.interactor.FiveDaysInteractor
import com.example.weekly_weather.repository.FiveDaysRemoteRepository
import com.example.weekly_weather.repository.FiveDaysRepository
import com.example.weekly_weather.ui.FiveDaysPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object FiveDaysModule : InjectionModule {
    override fun create() = module {
        single { get<Retrofit>().create(FiveDaysWeatherApi::class.java) }
        single<FiveDaysRepository> { FiveDaysRemoteRepository(get()) }
        single { FiveDaysRemoteRepository(get()) } bind FiveDaysRemoteRepository::class
        factory { FiveDaysInteractor(get()) }

        viewModel { FiveDaysPresenter(get()) }
    }
}