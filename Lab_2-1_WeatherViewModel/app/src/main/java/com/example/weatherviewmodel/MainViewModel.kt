package com.example.weatherviewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
  val name: MutableState<String> = mutableStateOf("")

  val temp: MutableState<Double> = mutableStateOf(0.0)
  val description: MutableState<String> = mutableStateOf("")

  val pressedButton: MutableState<Boolean> = mutableStateOf(false)

  private val _forecastState = MutableLiveData<ForecastState>()
  val forecastState: MutableLiveData<ForecastState> = _forecastState

  fun fetchForecast(){
    viewModelScope.launch {
        try {
          println("Getting response...")
          val forecastService = CreateForecastService(name.value)
          val response = forecastService.getForecast()

          name.value = response.name.toString()
          temp.value = response.main.temp.toDouble()
          description.value = response.weather[0].description.toString()

          _forecastState.setValue(
            ForecastState(
            loading = false,
            temp = response.main.temp,
            name = response.name,
            description = response.weather[0].description
          )
          )
        } catch (e: Exception) {
          _forecastState.value = _forecastState.value?.copy(
            loading = false,
            error = "Error fetching forecast: ${e.message}"
          )
        }
    }
  }

  data class ForecastState(
    val loading: Boolean? = true,
    val temp: Double,
    val name: String,
    val description: String,
    val error: String? = null
  )
}

