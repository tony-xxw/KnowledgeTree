package com.wynne.other.grammar

import android.util.Log


fun main(args: Array<String>) {
    val tag = "Grammar"
    val test = "Wynne"

    MyClass()
  }


class MyClass{

    companion object MyClass{
        val MY :String get() = "my"
        fun printLog(){
            print("2222222222222222222")
        }
    }

    init {
        println("1111111111111 MyClass")
    }


}


