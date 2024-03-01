package com.example.retrofitrickandmorty

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun CharactersScreen(modifier: Modifier = Modifier){
  val charactersViewModel: MainViewModel = viewModel()
  val viewState by charactersViewModel.charactersState

  Box(modifier = Modifier.fillMaxSize()){
    when{
      viewState.loading ->{
        CircularProgressIndicator(modifier.align(Alignment.Center))
      }

      viewState.error != null ->{
        Text("ERROR OCCURED:\nviewState.error.toString())")
      }

      else -> {
        CharacterScreen(characters = viewState.characters)
      }
    }
  }
}

@Composable
fun CharacterScreen(characters: List<Result>){
  LazyVerticalGrid(GridCells.Fixed(1), modifier = Modifier.fillMaxSize()){
    items(characters){
      character ->
        CharacterItem(character = character)
    }
  }
}

@Composable
fun CharacterItem(character: Result){
  Row (
    modifier = Modifier
      .padding(8.dp)
      .fillMaxSize()
  ) {
    Image(
      painter =
      rememberAsyncImagePainter(character.image),
      contentDescription = character.name,
      modifier = Modifier
        .height(128.dp)
        .width(128.dp)
        .aspectRatio(1f))
    
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = character.name)

    Spacer(modifier = Modifier.width(8.dp))
    Text(text = character.species)
  }
}