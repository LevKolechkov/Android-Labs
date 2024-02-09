package com.example.retrofitforecaster

import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.AndroidFont
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForecastScreen(modifier: Modifier = Modifier){
  val recipeViewModel: MainViewModel = viewModel()
  val viewstate by recipeViewModel.forecastState
  Box(modifier = Modifier.fillMaxSize()){
    when{
      viewstate.loading ->{
        CircularProgressIndicator(modifier.align(Alignment.Center))
      }
    }
    Column {
      SetLabel()
      when{
        viewstate.error != null ->{
          Text("ERROR OCCURED\n${viewstate.error}\nПолученный список:\n${viewstate.list.toString()}")
        }
        else -> {
          CategoryScreen(detailsOfForecasts = viewstate.list)
        }
      }
    }
  }
}

@Composable
fun CategoryScreen(detailsOfForecasts: List<Details>){
  LazyVerticalGrid(
    GridCells.Fixed(1),
    modifier = Modifier.fillMaxWidth()){
    items(detailsOfForecasts){
        detailsOfForecasts ->
      when {
        detailsOfForecasts.main.temp <= 0 -> ColdForecastItem(detail = detailsOfForecasts)

        detailsOfForecasts.main.temp > 0 -> HotForecastItem(detail = detailsOfForecasts)
      }
    }
  }
}

// Если холодно
@Composable
fun ColdForecastItem(detail: Details){
  Box(modifier = Modifier.background(Color(226,243,249))){
    Column {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(64.dp)
          .border(
            border = BorderStroke(2.dp, Color.Gray)
          ),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = detail.dt_txt,
          color = Color.Black,
          modifier = Modifier.padding(8.dp),
          style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Text(
          text = ("${detail.main.temp.toString()}\u00B0C"),
          color = Color.Black,
          modifier = Modifier.padding(8.dp),
          style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Image(
          painter = painterResource(id = R.drawable.cold_img),
          contentDescription = "",
          modifier = Modifier.height(48.dp))
      }
    }
  }
}

// Если горячо
@Composable
fun HotForecastItem(detail: Details){
  Box(modifier = Modifier.background(Color(249,226,226))){
    Column {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(64.dp)
          .border(
            border = BorderStroke(2.dp, Color.Gray)
          ),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = detail.dt_txt,
          color = Color.Black,
          modifier = Modifier.padding(8.dp),
          style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Text(
          text = ("${detail.main.temp.toString()}\u00B0C"),
          color = Color.Black,
          modifier = Modifier.padding(8.dp),
          style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Image(
          painter = painterResource(id = R.drawable.hot_img),
          contentDescription = "",
          modifier = Modifier.height(48.dp))
      }
    }
  }
}

@Composable
fun SetLabel(){
  val customTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 32.sp,
    color = Color.White
  )

  Box(modifier = Modifier
    .fillMaxWidth()
    .height(56.dp)
    .background(Color(106, 90, 205))
  ){
    Row (
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      horizontalArrangement = Arrangement.Absolute.Left
    ){
      Text(text = "Shklov", style = customTextStyle)
    }
  }
}