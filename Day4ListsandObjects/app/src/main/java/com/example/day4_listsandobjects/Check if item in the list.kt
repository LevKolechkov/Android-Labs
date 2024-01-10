package com.example.day4_listsandobjects

fun main () {
  val shoppingImmutableList = listOf("Processor", "RAM", "Graphics Card RTX 3060", "SSD")

  val shoppingMutableList = mutableListOf("Processor", "RAM", "Graphics Card", "SSD")

  shoppingMutableList.add("Cooling System")

  shoppingMutableList.remove("Graphics Card RTX 3060")

  shoppingMutableList.add("Graphics Card RTX 4090")

  println(shoppingMutableList)

  val hasRam = shoppingImmutableList.contains("RAM")

  if (hasRam) {
    println("Has RAM in the list")
  } else {
    println("Doesnt have RAM in the list")
  }
}