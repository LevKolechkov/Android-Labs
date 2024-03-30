package com.example.roomdatabase

import android.app.Application
import com.example.roomdatabase.data.MainDB

class App : Application() {
  val database by lazy { MainDB.createDataBase(this) }
}