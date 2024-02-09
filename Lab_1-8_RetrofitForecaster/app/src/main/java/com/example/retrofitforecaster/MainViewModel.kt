package com.example.retrofitforecaster

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

  private val _forecastState = mutableStateOf(ForecastState())
  val forecastState: State<ForecastState> = _forecastState

  init {
    fetchForecast()
  }

  private fun fetchForecast(){
    viewModelScope.launch {
      try {
        val response = forecastService.getForecast()
        _forecastState.value = _forecastState.value.copy(
          list = response.list,
          loading = false,
          error = null
        )
      } catch (e: Exception) {
        _forecastState.value = _forecastState.value.copy(
          loading = false,
          error = "Error fetching forecast: ${e.message}"
        )
      }
    }
  }

  data class ForecastState(
    val loading: Boolean = true,
    val list: List<Details> = emptyList(),
    val error: String? = null
  )
}