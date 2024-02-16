package com.example.weatherviewmodel

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForecastApp(){
  val forecastViewModel: MainViewModel = viewModel()
  Column (
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    OutlinedTextField(value = forecastViewModel.name.value, onValueChange = {
      forecastViewModel.name.value = it
    })
    Button(onClick = {
      forecastViewModel.pressedButton.value = true
      forecastViewModel.fetchForecast()
      println("Name: ${forecastViewModel.forecastState.value?.name}")
      println("Temperature: ${forecastViewModel.forecastState.value?.temp}")
      println("Description: ${forecastViewModel.forecastState.value?.description}")
    }) {
      Text(text = "GET FORECAST")
    }
    Box(modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)){
      when{
        !forecastViewModel.pressedButton.value -> {
          Text(modifier = Modifier.align(Alignment.Center),text = "You didn't press button yet")
        }

        forecastViewModel.pressedButton.value == true && forecastViewModel.forecastState.value?.loading == true -> {
          CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        forecastViewModel.forecastState.value?.error != null -> {
          Text(text = "Error with fetching forecast")
        }
        else -> {
          forecastBox(forecastViewModel = forecastViewModel)
        }
      }
    }
  }
}

@Composable
fun forecastBox(forecastViewModel: MainViewModel){
  Column {
    Text(text = "${forecastViewModel.name.value}")
    Text(text = "Temperature: ${forecastViewModel.temp.value}°C")
    Text(text = "Description: ${forecastViewModel.description.value}")
  }
}

@Preview
@Composable
fun ForecastPreview(){
  ForecastApp()
}