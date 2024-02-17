package com.example.weatherviewmodel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun CreateRetrofit(name: String): Retrofit {
  return Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}

fun CreateForecastService(name: String): ApiService{
  return CreateRetrofit(name).create(ApiService::class.java)
}

interface ApiService{
  @GET("weather?&appid=b66225f1f146bffbd338fa80cf3e0d0e&units=metric")
  suspend fun getForecast(@Query("q") city: String):Forecast
}
