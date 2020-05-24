package com.wynne.other.grammar

import com.wynne.knowledge.base.utils.LogUtil
import kotlin.properties.Delegates

interface KotlinInterface {
    val prop: Int

    val propertyWithImplementation: String
        get() = "foo"

    fun bar()

    fun foo() {
        LogUtil.d(prop.toString())
        LogUtil.d(Box("A").value)

        Box("word").date ="hellow"
    }


}


class Box<T>(t:T){
    var value =t

    //字段监听
    var date :String by Delegates.observable("time"){ _, oldValue, newValue ->
        LogUtil.d("$oldValue -- $newValue")
    }
}


