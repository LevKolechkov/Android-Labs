package com.example.weatherviewmodel

import androidx.compose.runtime.MutableState
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

fun CreateRetroFit(name: MutableState<String>):Retrofit {
  return Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/weather?q=" +
          "${name}&appid=b66225f1f146bffbd338fa80cf3e0d0e")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}

fun CreateForecastService(name: MutableState<String>): ApiService{
  return CreateRetroFit(name).create(ApiService::class.java)
}

interface ApiService{
  @GET("")
  suspend fun getForecast():Forecast

}