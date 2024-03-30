package com.example.roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table")
data class NameEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int? = null,
  val name: String,

)