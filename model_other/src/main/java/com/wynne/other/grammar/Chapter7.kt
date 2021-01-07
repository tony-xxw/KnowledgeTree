package com.wynne.other.grammar

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.view.View
import android.widget.Toast

fun main() {

    chapterSeven()
    println()
    chapterSeven1()
    println()
    chapterSeven2()
    println()
    chapterSeven3()
}

fun chapterSeven3() {
    println("chapter 7.4")

    val base: Base = Extension()
    base.foo()

    val extension = Extension()
    extension.overloading(base)


}

open class Base {
    open fun foo() {
        println("base kotlin")
    }
}

class Extension : Base() {
    override fun foo() {
        println("Extension kotlin")
    }

    fun overloading(extension: Extension) {
        println("Extension")
    }

    fun overloading(base: Base) {
        println("Base")
    }
}

fun chapterSeven2() {
    println("chapter 7.3")

}

class NetWorkUtil {

    fun Context.isMobileConnected(): Boolean {
        val mNetworkInfo = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = mNetworkInfo.getNetworkInfo(Network.CONTENTS_FILE_DESCRIPTOR)
        if (network != null) {
            return network.isAvailable
        }
        return false
    }
}

inline fun View.showToast(message: String, action: () -> Unit = {}) {
    val activity = context
    Toast.makeText(activity as Activity, message, Toast.LENGTH_SHORT).show()
}

fun chapterSeven1() {
    println("chapter 7.2")
    val mutableList = mutableListOf<Int>(1, 2, 3)
    mutableList.exchange(1, 2)
    println("扩展函数: $mutableList")
    println("扩展属性 ${mutableList.sumIsEven}")

    val a = Test()
    val b = "11"


    val kot = Kots()
    kot.dealStu()


}

class Kots {
    val stu: Stu? = Stu(name = "xxw", age = 18, sex = "m", score = 100)
    var age = 10
    fun dealStu() {
//        val result = stu?.run {
//            age + this.age
//            println("this age ${age}")
//            println("stu age ${this.age}")
//            this
//        }
//        println("Kot $result")
//        val result = stu?.let {
//            age + this.age
//            println("this age ${age}")
//            println("stu age ${this.age}")
//            "this"
//        }
//        println("Kot $result")

//        val result = stu?.also { t ->
//            this.age += t.age
//            println("this age ${this.age}")
//            println("stu age ${t.age}")
//            "1"
//        }
//        println("Kot $result")

        val result = stu?.run {
            this@Kots.age += age
            println("this age ${this.age}")
            "1"
        }
        println("Kot $result")

//        val result = stu?.let {
//            it.takeIf { it.age > 10 }?.run {
//                this.age = 12
//                this
//            }
//        }
//        println("Kot takeIf  ${result}")

    }
}

val MutableList<Int>.sumIsEven: Boolean
    get() = this.sum() % 2 == 0

fun MutableList<Int>.exchange(from: Int, to: Int) {
    val temp = this[from]
    this[from] = this[to]
    this[to] = temp
}

class Test() {
    val b = ""

    companion object {
        val a = ""

        fun String.isLength() {
            println("length ${a.length}")
        }
    }

    fun String.sAength() {
        println("length")
    }
}

fun chapterSeven() {
    println("Chapter 7.1")
    println("子类多态 ${Len(1).let { it.plusThat(it) }.v}")
    println("+=${Len(1) + Len(1)}")
    println("-=${Len(3) - Len(1)}")
    println("*=${Len(1) * Len(2)}")
    println("/=${Len(4) / Len(2)}")
    println("%=${Len(4) % Len(2)}")
}

interface Summable<T> {
    fun plusThat(that: T): T
}

data class Len(val v: Int) : Summable<Len> {
    override fun plusThat(that: Len) = Len(this.v + that.v)
}

operator fun Len.plus(that: Len): Len {
    return Len(this.v + that.v)
}

operator fun Len.minus(that: Len): Int {
    return this.v - that.v
}

operator fun Len.times(that: Len): Int {
    return this.v * that.v
}

operator fun Len.div(that: Len): Int {
    return this.v / that.v
}

operator fun Len.rem(that: Len): Int {
    return this.v % that.v
}