package com.example.drawapp

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonPanel(
  onClick: (Color) -> Unit,
  onLineWidthChange: (Float) -> Unit,
  onBackClick: () -> Unit,
  onImportImage: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.LightGray),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    ColorList { color ->
      onClick(color)
    }
    CustomSlider { lineWidth ->
      onLineWidthChange(lineWidth)
    }
    ButtonPanel(
      onClick = onBackClick,
      onImportImage = onImportImage)
    Spacer(modifier = Modifier.height(5.dp))
  }
}

@Composable
fun ColorList(onClick: (Color) -> Unit) {
  var colors = listOf(
    Color.Blue,
    Color.Red,
    Color.Black,
    Color.Magenta,
    Color.Yellow,
    Color.Green
  )

  LazyRow(
    modifier = Modifier.padding(10.dp)
  ) {
    items(colors) { color ->
      Box(
        modifier = Modifier
          .padding(end = 10.dp)
          .clickable {
            onClick(color)
          }
          .size(40.dp)
          .background(color, CircleShape)
      )
    }
  }
}

@Composable
fun CustomSlider(onChange: (Float) -> Unit) {
  var position by remember {
    mutableStateOf(0.05f)
  }
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Text("Line width: ${(position * 100).toInt()}")
    Slider(
      value = position,
      onValueChange = {
        val tempPos = if (it > 0) it else 0.01f
        position = tempPos
        onChange(tempPos * 100)
      })
  }
}

@Composable
fun ButtonPanel(onClick: () -> Unit, onImportImage: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(10.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(
      modifier = Modifier
        .clip(CircleShape)
        .background(Color.White),
      onClick = {
        onClick()
      }) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null
      )
    }
    IconButton(
      modifier = Modifier
        .clip(CircleShape)
        .background(Color.White),
      onClick = {
        onImportImage()
      }) {
      Icon(
        imageVector = Icons.Default.AddCircle,
        contentDescription = null
      )
    }
  }
}