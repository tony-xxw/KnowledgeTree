package com.wynne.advanced.dagger

import javax.inject.Inject

class Person1 @Inject constructor() {

    @Inject
    lateinit var baozi: Baozi

    @Inject
    lateinit var noodle: Noodle

    @Inject
    lateinit var resturant:String

    fun eat(): String {
        val sb = StringBuffer()
        sb.append("我从 ");
        sb.append(resturant.toString());
        sb.append("订的外卖，");
        sb.append("我吃的是 ");
        if (baozi != null) {
            sb.append(baozi.toString())
        }
        if (noodle != null) {
            sb.append(noodle.toString())
        }
        return sb.toString()
    }
}