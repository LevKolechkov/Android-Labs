package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [
    NameEntity::class
  ],
  version = 1
)
abstract class MainDB : RoomDatabase() {
  abstract val dao: com.example.roomdatabase.Dao
  companion object{
    fun createDataBase(context: Context): MainDB{
      return Room.databaseBuilder(
        context = context,
        MainDB::class.java,
        "my_database"
      ).build()
    }
  }
}