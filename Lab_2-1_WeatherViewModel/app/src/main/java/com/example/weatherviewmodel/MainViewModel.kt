package com.example.weatherviewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
  val name: MutableState<String>
) : ViewModel() {
  private val _forecastState = MutableLiveData<ForecastState>()
  val forecastState: LiveData<ForecastState> = _forecastState

  fun fetchForecast(){
    viewModelScope.launch {
      try {
        var forecastService = CreateForecastService(name)
        var response = forecastService.getForecast()
        _forecastState.value = _forecastState.value?.copy(
          loading = false,
          temp = response.main.temp,
          name = response.name,
          description = response.weather[0].description
        )
      } catch (e: Exception) {
        _forecastState.value = _forecastState.value?.copy(
          loading = false,
          error = "Error fetching forecast: ${e.message}"
        )
      }
    }
  }
}

data class ForecastState(
  val loading: Boolean = true,
  val temp: Double,
  val name: String,
  val description: String,
  val error: String? = null
)