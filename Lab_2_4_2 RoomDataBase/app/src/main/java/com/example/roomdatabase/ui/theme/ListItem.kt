package com.example.roomdatabase.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roomdatabase.data.NameEntity

@Composable
fun ListItem(
  item: ItemNameEntity,
  onClick: (ItemNameEntity) -> Unit,
  onDelete: (NameEntity) -> Unit
) {
  Card (
    modifier = Modifier
      .fillMaxWidth()
      .padding(5.dp)
      .clickable {
        item.expanded.value = true

        onClick(item)
      }
  ) {
    if (!item.expanded.value) NotExpandedItem(item = item, onDelete = onDelete)
    else ExpandedItem(item = item, onDelete = onDelete)
  }
}

@Composable
fun NotExpandedItem(
  item: ItemNameEntity,
  onDelete: (NameEntity) -> Unit
){
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      item.name.value,
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .padding(10.dp)
    )
    IconButton(onClick = {
      onDelete(NameEntity(
        id = item.id,
        name = item.name.value,
        desc = item.desc.value
      ))
    }) {
      Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = "Delete")
    }
  }
}

@Composable
fun ExpandedItem(
  item: ItemNameEntity,
  onDelete: (NameEntity) -> Unit
){
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column (modifier = Modifier
      .fillMaxWidth()
      .weight(1f)
      .padding(10.dp)) {
      Text(
        item.name.value
      )
      OutlinedTextField(
        value = item.desc.value,
        onValueChange = { item.desc.value = it },
        label = { Text("Description") }
      )
    }
    IconButton(onClick = {
      onDelete(NameEntity(
        id = item.id,
        name = item.name.value,
        desc = item.desc.value
      ))
    }) {
      Icon(
        imageVector = Icons.Default.Delete,
        contentDescription = "Delete")
    }
  }
}