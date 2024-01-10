package com.example.day4_listsandobjects

fun main() {
  val numbers = mutableListOf<Int>(6, 1, 56, 151, 25)

  for (number in 0 until numbers.size) {
    numbers[number] = numbers[number] * 2
  }

  println(numbers)
}