package com.example.weatherviewmodel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForecastScreen(){
  var forecastViewModel: MainViewModel = viewModel()

  Box(modifier = Modifier.fillMaxSize()){
    Column {
      OutlinedTextField(value = forecastViewModel.name.value, onValueChange = {
        forecastViewModel.name.value = it
      })

      Box(modifier = Modifier.fillMaxWidth().height(30.dp))
    }
  }
}
