package com.wynne.http

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log

class MyService(var handle: Handler) : Service() {
    private var handler: Handler? = null

    init {
        this.handle = handle
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val stopDelayed: Long = intent.getLongExtra("Delayed", 2000)
        handler?.postDelayed(Runnable {
            Log.d("XXW", "11111111111")
            val LaunchIntent: Intent = packageManager.getLaunchIntentForPackage(application.packageName)
            LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(LaunchIntent)
            this@MyService.stopSelf()
        }, stopDelayed)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}