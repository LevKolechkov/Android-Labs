package com.example.retrofitforecaster

data class ForeCastResponse(val forecasts: Forecast)

data class Forecast (
  val cod: String,
  val message: Int,
  val cnt: Int,
  val list: List<Details>,
  val city: City
)

data class City (
  val id: Int,
  val name: String,
  val coord: Coordinate,
  val country: String,
  val population: Int,
  val timezone: Int,
  val sunrise: Long,
  val sunset: Long
)

data class Coordinate (
  val lat: Double,
  val lon: Double
)

data class Details (
  val dt: Long,
  val main: Main,
  val weather: List<Weather>,
  val clouds: Clouds,
  val wind: Wind,
  val visibility: Int,
  val pop: Double,
  val sys: Sys,
  val dt_txt: String
)

data class Main (
  val temp: Double,
  val feels_like: Double,
  val temp_min: Double,
  val temp_max: Double,
  val pressure: Int,
  val sea_level: Int,
  val grnd_level: Int,
  val humidity: Int,
  val temp_kf: Double
)

data class Weather (
  val id: Int,
  val main: String,
  val description: String,
  val icon: String
)

data class Clouds (
  val all: Int
)

data class Wind (
  val speed: Double,
  val deg: Int,
  val gust: Double
)

data class Sys(
  val pod: String
)




