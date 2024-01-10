package com.example.day4_listsandobjects

fun main() {
  // Immutable list
  val shoppingImmutableList = listOf("Processor", "RAM", "Graphics Card RTX 3060", "SSD")

  // Mutable list
  val shoppingMutableList = mutableListOf("Processor", "RAM", "Graphics Card", "SSD")

  shoppingMutableList.add("Cooling System")

  shoppingMutableList.remove("Graphics Card RTX 3060")

  shoppingMutableList.add("Graphics Card RTX 4090")

  println(shoppingMutableList)
}