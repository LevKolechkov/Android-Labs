package com.example.retrofitrickandmorty

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking


class MainViewModel: ViewModel() {

  companion object {
    private val _charactersState = mutableStateOf(CharactersState())
    val charactersState: State<CharactersState> = _charactersState

    val baseURL:  String = "https://rickandmortyapi.com/api/"
    val endpoint: String = "character"

    init {
      fetchCharacters()
    }

    private fun fetchCharacters(){
      runBlocking {
        try {
          val response = recipeService.getCharacters(paramValue = endpoint)
          _charactersState.value = _charactersState.value.copy(
            characters = response.results,
            loading = false,
            error = null
          )
        } catch (e: Exception){
          _charactersState.value = _charactersState.value.copy(
            loading = false,
            error = "Error fetching character: ${e.message}"
          )
        }
      }
    }
  }
}

data class CharactersState(
  val loading: Boolean = true,
  val characters: List<Result> = emptyList(),
  val error: String? = null
)