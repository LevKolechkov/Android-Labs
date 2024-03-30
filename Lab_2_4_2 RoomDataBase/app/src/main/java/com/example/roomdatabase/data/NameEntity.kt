package com.example.roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class NameEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int? = null,
  val name: String,
  val desc: String
)