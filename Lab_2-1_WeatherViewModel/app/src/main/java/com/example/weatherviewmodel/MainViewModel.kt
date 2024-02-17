package com.example.weatherviewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
  val name: MutableState<String> = mutableStateOf("")

  val responsed_name: MutableState<String> = mutableStateOf("")

  private val temp: MutableState<Double> = mutableStateOf(0.0)
  private val description: MutableState<String> = mutableStateOf("")
  private val weather: MutableState<List<Weather>> = mutableStateOf(emptyList())

  val pressedButton: MutableState<Boolean> = mutableStateOf(false)

  private val _forecastState = MutableLiveData<ForecastState>(ForecastState())
  val forecastState: MutableLiveData<ForecastState> = _forecastState

  fun fetchForecast(){
    viewModelScope.launch {
        try {
          println("Getting response...")
          val forecastService = CreateForecastService(name.value)
          val response = forecastService.getForecast(name.value)

          responsed_name.value = response.name
          temp.value = response.main.temp
          weather.value = response.weather
          description.value = weather.value[0].description

          _forecastState.setValue(
            _forecastState.value?.copy(
            loading = false,
            temp = temp.value,
            name = responsed_name.value,
            weather = weather.value,
            description = description.value
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
    var loading: Boolean? = true,
    var temp: Double = 0.0,
    var name: String = "",
    var description: String = "",
    var weather: List<Weather> = emptyList(),
    var error: String? = null
  )
}

