package com.example.retrofitforecaster

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
  .addConverterFactory(GsonConverterFactory.create())
  .build()

val forecastService = retrofit.create(ApiService::class.java)

interface ApiService{
  @GET("/data/2.5/forecast?id=622034&appid=b66225f1f146bffbd338fa80cf3e0d0e&units=metric")
  suspend fun getForecast():Forecast

}