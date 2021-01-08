package com.wynne.myapplication

import android.app.Person
import android.view.animation.Animation
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties


fun main() {

    chapterEight()
    println()
    chapterEight1()
}

fun chapterEight1() {


    val annotations = Hero::class.annotations.find { it is Cache } as Cache?
    annotations.also {
        println("name ${annotations?.namespace}")
        println("exp ${annotations?.expires}")
    }


}



annotation class Cache(val namespace: String, val expires: Int)
annotation class CacheKey(val keyName: String, val buckets: IntArray)

@Cache(namespace = "hero", expires = 3600)
data class Hero(@property:CacheKey(keyName = "heroName", buckets = [1, 2, 3])
                val name: String,
                @field:CacheKey(keyName = "atk", buckets = [1, 2, 3])
                val attack: Int,
                @get:CacheKey(keyName = "def", buckets = [1, 2, 3])
                val defense: Int, val initHp: Int)

fun chapterEight() {
    println("chapter8.2")
    val _l = Succ(Nat.Companion.Zero).prev
    println("_l $_l")
    val preceed = _l::class.members.find { it.name == "preceed" }
//    println(preceed?.call(_l, _l) == Nat.Companion.Zero)
    //反射
//    KMutablePropertyShow()

}

class Person(var name: String, var age: Int, var address: String)

fun KMutablePropertyShow() {
    val p = Person("向鲜汶", 25, "Shenzhen")
    val props = p::class.memberProperties
    for (prop in props) {
        when (prop) {
            is KMutableProperty<*> -> prop.setter.call(p, "Hefei")
            else -> prop.call(p)
        }
    }
    println("KMutablePropertyShow ${p.address}")

}

sealed class Nat {
    companion object {
        object Zero : Nat()
    }

    val Companion._0
        get() = Zero

    fun <A : Nat> Succ<A>.preceed(): A {
        return this.prev
    }
}

data class Succ<N : Nat>(val prev: N) : Nat()

fun <A : Nat> Nat.plus(other: A): Nat = when {
    other is Succ<*> -> Succ(this.plus(other.prev))
    else -> this
}