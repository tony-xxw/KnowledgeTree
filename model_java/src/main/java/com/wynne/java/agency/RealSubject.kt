package com.wynne.java.agency

import android.util.Log

class RealSubject : Subject {
    override fun hello(str: String) {
        Log.d("XXW", "hello: " + str)
    }

    override fun rent() {
        Log.d("XXW", "i want rent my house")
    }
}