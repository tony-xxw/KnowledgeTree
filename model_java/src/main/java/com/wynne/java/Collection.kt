package com.wynne.java

import android.util.ArrayMap
import android.util.SparseArray
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.full.declaredMembers

class Reflect(){

    fun main() {
        println(this.javaClass.canonicalName)
    }
}

fun main() {

    listSample()
    setSample()
//    mapSample()

    Reflect().main()
    val declaredMembers = Reflect::class

}

fun mapSample() {
    println()
    val mutableMapOf: MutableMap<String, String> = mutableMapOf<String, String>()
    mutableMapOf["1"] = "a"
    mutableMapOf["2"] = "b"
    mutableMapOf["3"] = "c"
    mutableMapOf["4"] = "c"
    for (mutableEntry in mutableMapOf) {
        println("key: ${mutableEntry.key}  value: ${mutableEntry.value}")
    }

    val hashMapOf = hashMapOf<String, Any>()
    hashMapOf["1"] = "a"
    hashMapOf["2"] = "b"
    hashMapOf["3"] = "c"
    hashMapOf["4"] = "c"


    val concurrentHashMap = ConcurrentHashMap<String, String>()
    concurrentHashMap["1"] = "a"
    val treeMap = TreeMap<String, String>()
    treeMap["11"] = "a"

    val hashtable = Hashtable<String, String>()
    hashtable["11"] = "b"

    val sparseArray = SparseArray<String>()
    sparseArray.put(1, "10")

    val arrayMap = ArrayMap<String, String>()
    arrayMap.put("11", "2")
}


fun setSample() {
    println()
    val mutableSetOf = mutableSetOf<String>()
    mutableSetOf.add("a")
    mutableSetOf.add("b")
    mutableSetOf.add("c")

    for ((index, item) in mutableSetOf.withIndex()) {
        println("set: $index item: $item")
    }
}

fun listSample() {
    println()
    val mutableListOf = mutableListOf<String>()
    mutableListOf.add("a")
    mutableListOf.add("b")
    mutableListOf.add("c")
    for ((index, item) in mutableListOf.withIndex()) {
        println("list:  item $item index: $index")
    }

}
