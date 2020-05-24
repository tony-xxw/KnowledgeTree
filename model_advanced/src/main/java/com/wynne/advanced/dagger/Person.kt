package com.wynne.advanced.dagger

import javax.inject.Inject

class Person @Inject constructor() {

    @Inject
    lateinit var baozi: Baozi

    @Inject
    lateinit var noodle: Noodle

    fun eat(): String {
        val sb = StringBuffer()
        sb.append("我吃的是: ")
        if (baozi != null) {
            sb.append(baozi.toString())
        }
        if (noodle != null) {
            sb.append(noodle.toString())
        }
        return sb.toString()
    }
}