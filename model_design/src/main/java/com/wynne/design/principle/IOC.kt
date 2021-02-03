package com.wynne.design.principle

import android.util.Log

/**
 * 控制反转
 */
abstract class IOC {

    fun run() {
        if (doTest()) {
            Log.d("XXW", "dotest success")
        } else {
            Log.d("XXW", "dotest failed")
        }
    }

    abstract fun doTest(): Boolean
}

class JunitApplication() {
    companion object {
        var list = mutableListOf<IOC>()

        fun register(ioc: IOC) {
            list.add(ioc)
        }
    }
}

class IOCSample : IOC() {
    override fun doTest(): Boolean = true
}