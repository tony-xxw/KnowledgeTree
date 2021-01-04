package com.wynne.other.kotlin


fun main() {
    val list = listOf<String>("apple", "banana", "pear", "watermelon")

//    print(list.maxBy { it.length })


    val result = list.maxBy { it.length }
    print(result)
}