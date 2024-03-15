package com.example.retrofitrickandmorty

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder().baseUrl(MainViewModel.baseURL)
  .addConverterFactory(GsonConverterFactory.create())
  .build()

val recipeService = retrofit.create(ApiCharacterService::class.java)

interface ApiCharacterService{

  @GET("{paramValue}")
  suspend fun getCharacters(@Path("paramValue") paramValue: String):Characters

//  @GET("api/character")
//  suspend fun getCharacters():Characters
}