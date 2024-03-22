package com.example.roomdatabase

import android.app.Application
import com.example.roomdatabase.data.MainDb

class App: Application() {
  val dataBase by lazy { MainDb.createDataBase(this) }
}