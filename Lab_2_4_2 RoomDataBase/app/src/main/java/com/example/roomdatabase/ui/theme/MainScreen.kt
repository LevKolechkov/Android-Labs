package com.example.roomdatabase.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdatabase.MainViewModel
import com.example.roomdatabase.data.NameEntity

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
  mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
  val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
  
  Column (
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    Row(
      modifier = Modifier.
        fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ){
      TextField(
        value = mainViewModel.newText.value,
        onValueChange = {
          mainViewModel.newText.value = it
        },
        label = {
          Text(text = "Name")
        },
        modifier = Modifier.weight(1f),
        colors = TextFieldDefaults.textFieldColors(
          containerColor = Color.White
        )
      )
      IconButton(
        onClick = {
          mainViewModel.insertItem()
        }
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Add")

      }
    }
    Spacer(modifier = Modifier.height(5.dp))
    LazyColumn(
      modifier = Modifier.fillMaxWidth()
    ) {
      items(itemsList.value){ nameEntityItem ->
        val item = ItemNameEntity(
          id = nameEntityItem.id,
          name = mutableStateOf(nameEntityItem.name),
          desc = mutableStateOf(nameEntityItem.desc)
        )

        ListItem(
          item,
          onClick = {
            mainViewModel.nameEntity = NameEntity(
              id = item.id,
              name = item.name.value,
              desc = item.desc.value)

            mainViewModel.newText.value = it.name.value
            mainViewModel.newDesc.value = it.desc.value
          },
          onDelete = {
            mainViewModel.deleteItem(it)
          },
          onClickSaveDesc = {
            mainViewModel.insertItem()
          })
      }
    }
  }
}

data class ItemNameEntity(
  val id: Int? = null,
  val name: MutableState<String> = mutableStateOf(""),
  var desc: MutableState<String> = mutableStateOf(""),
  var expanded: MutableState<Boolean> = mutableStateOf(false)
)