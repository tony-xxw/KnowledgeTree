package com.wynne.other.kotlin

import android.util.Log
import kotlin.properties.Delegates

class Chapter3 {

    var test by Delegates.notNull<Int>()
    fun show() {

        test = 1
        Log.d("Chapter2", "test value $test")
        test = 2

    }
}