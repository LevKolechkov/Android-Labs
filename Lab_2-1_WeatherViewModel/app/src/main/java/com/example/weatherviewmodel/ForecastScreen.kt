package com.example.weatherviewmodel

import androidx.compose.foundation.Image
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
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@Composable
fun ForecastApp(forecastViewModel: MainViewModel){
  Column (
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    OutlinedTextField(value = forecastViewModel.name.value, onValueChange = {
      forecastViewModel.pressedButton.value = false
      forecastViewModel.name.value = it
    })
    Button(onClick = {
      forecastViewModel.pressedButton.value = true
      forecastViewModel.forecastState.value?.loading = true
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

        }

        forecastViewModel.forecastState.value?.error != null -> {
          Text(text = "Error with fetching forecast")
        }
        else -> {
          if (forecastViewModel.responsed_name.value == ""){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
          } else {
            forecastBox(forecastViewModel = forecastViewModel)
          }
        }
      }
    }
  }
}

@Composable
fun forecastBox(forecastViewModel: MainViewModel){
  Column {
    Text(text = forecastViewModel.responsed_name.value)
    Text(text = "Temperature: ${forecastViewModel.forecastState.value?.temp}°C")
    Text(text = "Description: ${forecastViewModel.forecastState.value?.description}")
    ImageFromUrl(url ="https://openweathermap.org/img/wn/" + (forecastViewModel.forecastState.value?.weather?.get(0)?.icon) + "@2x.png" )
  }
}

//"https://openweathermap.org/img/wn/" + weather.icon + "@2x.png"

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageFromUrl(url: String) {
  Image(
    painter = rememberImagePainter(
      data = url
    ),
    contentDescription = null
  )
}

@Preview(showBackground = true)
@Composable
fun ForecastPreview(){
  val forecastViewModel: MainViewModel = viewModel()
  ForecastApp(forecastViewModel)
}