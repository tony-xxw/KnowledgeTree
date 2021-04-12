package com.wynne.thread

import android.app.IntentService
import android.content.Intent
import android.util.Log

class IntentServiceImp : IntentService("IntentService") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("XXW", "当前线程名为: ${Thread.currentThread().name}  传递参数为: ${intent!!.getStringExtra("key")}")
    }
}