package com.example.onlineapppauseresume

data class WeatherData(
    val coord: Coord = Coord(0.0, 0.0),
    val weather: List<Weather> = emptyList(),
    val base: String = "",
    val main: Main = Main(0.0, 0.0, 0.0, 0.0, 0, 0),
    val visibility: Int = 0,
    val wind: Wind = Wind(0.0, 0),
    val clouds: Clouds = Clouds(0),
    val dt: Long = 0,
    val sys: Sys = Sys(0, 0, "", 0, 0),
    val timezone: Int = 0,
    val id: Long = 0,
    val name: String = "",
    val cod: Int = 0
)

data class Coord(
    val lon: Double = 0.0,
    val lat: Double = 0.0
)

data class Weather(
    val id: Int = 0,
    val main: String = "",
    val description: String = "",
    val icon: String = ""
)

data class Main(
    val temp: Double = 0.0,
    val feels_like: Double = 0.0,
    val temp_min: Double = 0.0,
    val temp_max: Double = 0.0,
    val pressure: Int = 0,
    val humidity: Int = 0
)

data class Wind(
    val speed: Double = 0.0,
    val deg: Int = 0
)

data class Clouds(
    val all: Int = 0
)

data class Sys(
    val type: Int = 0,
    val id: Int = 0,
    val country: String = "",
    val sunrise: Long = 0,
    val sunset: Long = 0
)
