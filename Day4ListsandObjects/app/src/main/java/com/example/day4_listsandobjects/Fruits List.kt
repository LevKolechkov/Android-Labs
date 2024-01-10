package com.example.day4_listsandobjects

fun main() {
  val fruitsList = mutableListOf<String>()

  fruitsList.add("Apple")
  fruitsList.add("Pineapple")

  fruitsList.remove("Pineapple")

  if (fruitsList.contains("Apple")) {
    println("I've found apple!")
  } else {
    println("I didn't found apple")
  }
}