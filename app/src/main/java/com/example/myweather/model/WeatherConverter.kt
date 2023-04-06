package com.example.myweather.model

import com.example.myweather.api.models.*

object WeatherConverter {
    fun fromNetwork(response: WeatherDataResponse?): WeatherData {
        return WeatherData(
            coord = response?.let { fromNetwork(it.coord) } ?: Coord(0.0, 0.0),
            weather = response?.let { fromNetwork(it.weather) } ?: emptyList(),
            base = response?.base ?: "",
            main = response?.let { fromNetwork(it.main) } ?: Main(0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0),
            visibility = response?.visibility ?: 0,
            wind = response?.let { fromNetwork(it.wind) } ?: Wind(0.0, 0, 0.0),
            clouds = response?.let { fromNetwork(it.clouds) } ?: Clouds(0),
            dt = response?.dt ?: 0,
            sys = response?.let { fromNetwork(it.sys) } ?: Sys(0, 0, "", 0, 0),
            timezone = response?.timezone ?: 0,
            id = response?.id ?: 0,
            name = response?.name ?: "",
            cod = response?.cod ?: 0
        )
    }

    private fun fromNetwork(response: CoordResponse): Coord {
        return Coord(
            lon = response.lon,
            lat = response.lat
        )
    }

    private fun fromNetwork(response: MainResponse): Main {
        return Main(
            temp = response.temp,
            feels_like = response.feels_like,
            temp_max = response.temp_max,
            temp_min = response.temp_min,
            pressure = response.pressure,
            humidity = response.humidity,
            sea_level = response.sea_level,
            grnd_level = response.grnd_level
        )
    }

    private fun fromNetwork(response: WindResponse): Wind {
        return Wind(
            speed = response.speed,
            deg = response.deg,
            gust = response.gust
        )
    }

    private fun fromNetwork(response: CloudsResponse): Clouds {
        return Clouds(
            all = response.all
        )
    }

    private fun fromNetwork(response: SysResponse): Sys {
        return Sys(
            id = response.id,
            type = response.type,
            country = response.country,
            sunrise = response.sunrise,
            sunset = response.sunset
        )
    }

    private fun fromNetwork(response: List<WeatherResponse>): List<Weather> {
        return response.map { data ->
            Weather(
                id = data.id,
                main = data.main,
                description = data.description,
                icon = data.icon
            )
        }
    }
}