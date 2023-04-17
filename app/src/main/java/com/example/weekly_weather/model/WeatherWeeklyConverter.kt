package com.example.weekly_weather.model

import com.example.myweather.api.models.CloudsResponse
import com.example.myweather.api.models.CoordResponse
import com.example.myweather.api.models.WeatherResponse
import com.example.myweather.api.models.WindResponse
import com.example.myweather.model.Clouds
import com.example.myweather.model.Coord
import com.example.myweather.model.Weather
import com.example.myweather.model.Wind
import com.example.weekly_weather.api.models.*

object WeatherWeeklyConverter {
    fun fromNetwork(response: FiveDaysResponse): FiveDays {
        return FiveDays(
            cod = response.cod,
            message = response.message,
            cnt = response.cnt,
            list = fromNetworkWeatherOneData(response.list),
            city = fromNetwork(response.city)
        )
    }


    private fun fromNetworkWeatherOneData(response: List<WeatherOneDataResponse>) =
        response.map { data ->
            FiveDaysWeather(
                dt = data.dt,
                main = fromNetwork(data.main),
                weather = fromNetworkWeather(data.weather),
                clouds = fromNetwork(data.clouds),
                wind = fromNetwork(data.wind),
                visibility = data.visibility,
                pop = data.pop,
                rain = fromNetwork(data.rain),
                sys = fromNetwork(data.sys),
                dt_txt = data.dataTime
            )
        }

    private fun fromNetwork(response: CityResponse) =
        City(
            id = response.id,
            name = response.name,
            coord = fromNetwork(response.coord),
            country = response.country,
            population = response.population,
            timeZone = response.timeZone,
            sunrise = response.sunrise,
            sunset = response.sunset
        )

    private fun fromNetworkWeather(response: List<WeatherResponse>) =
        response.map { data ->
            Weather(
                id = data.id,
                main = data.main,
                description = data.description,
                icon = data.icon
            )
        }

    private fun fromNetwork(response: MainResponse2) =
        Main(
            temp = response.temp,
            feels_like = response.feelsLike,
            temp_min = response.tempMin,
            temp_max = response.tempMax,
            pressure = response.pressure,
            sea_level = response.seaLevel,
            grnd_level = response.grndLevel,
            humidity = response.humidity,
            temp_kf = response.tempKf
        )

    private fun fromNetwork(response: CloudsResponse) =
        Clouds(
            all = response.all
        )

    private fun fromNetwork(response: WindResponse) =
        Wind(
            speed = response.speed,
            deg = response.deg,
            gust = response.gust
        )

    private fun fromNetwork(response: RainResponse?) =
        Rain(
            h3 = response?.h3 ?: 0.0
        )

    private fun fromNetwork(response: SysResponse2) =
        Sys(
            pod = response.pod
        )

    private fun fromNetwork(response: CoordResponse) =
        Coord(
            lon = response.lon,
            lat = response.lat
        )
}