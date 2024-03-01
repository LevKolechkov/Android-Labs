package com.example.retrofitrickandmorty

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
  .addConverterFactory(GsonConverterFactory.create())
  .build()

  val recipeService = retrofit.create(ApiService::class.java)

interface ApiService{

  @GET("character")
  suspend fun getCharacters():Characters
}