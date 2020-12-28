package com.wynne.android

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import com.wynne.knowledge.base.BaseApplication

class AndroidApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        val processName = obtainProcessName(this, android.os.Process.myPid())
        Log.d("XXW", processName)

    }

    override fun initModuleApp(application: Application) {}
    override fun initModuleData(application: Application) {}


    fun obtainProcessName(ctx: Context, pid: Int): String {
        val am = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runnings = am.runningAppProcesses ?: return ""

        runnings.forEach {
            if (it.pid == pid) return it.processName
        }
        return ""
    }
}