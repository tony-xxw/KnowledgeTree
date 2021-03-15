package com.wynne.thread

import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.Process
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_THREAD
import kotlinx.android.synthetic.main.activity_thread_android_layout.*

@Route(path = BASE_THREAD)
class ThreadAndroidActivity : BaseActivity() {

    companion object {
        val TAG: String = ThreadAndroidActivity::class.java.name
    }


    lateinit var adapter: MainAdapter
    var list = mutableListOf(
            MainData("Thread", R.drawable.icon_pomelo),
            MainData("线程池", R.drawable.icon_grape),
            MainData("AsyncTask", R.drawable.icon_apple),
            MainData("IntentService", R.drawable.icon_watermelon),
            MainData("HandlerThread", R.drawable.icon_peach),
            MainData("多进程通信Binder,AIDL,多进程", R.drawable.icon_pear))

    override fun initView() {
        toolBar.title = "知识体系"
        adapter = MainAdapter(this)
        adapter.mList = list
        rvThread.adapter = adapter

        adapter.listener = {
            when (list[it].name) {
                "Thread" -> {
                    practiceThreadTest()
                }
                "线程池" -> {
                    startActivity(Intent(this, ExecutorsActivity::class.java))
                }
                "AsyncTask" -> {
                    practiceAsyncTaskTest()
                }
                "IntentService" -> {
                    practiceIntentServiceTest()
                }
                "HandlerThread" -> {
                    practiceHandlerThreadTest()
                }
                "多进程通信Binder,AIDL,多进程" -> {
                }
            }
        }
    }

    override val layoutId: Int = R.layout.activity_thread_android_layout

    private fun practiceHandlerThreadTest() {

        val handlerThread = HandlerThread("handler-thread")
        handlerThread.start()
        val handler = Handler(handlerThread.looper) {
            it?.let { Log.d(TAG, "handler message $it   threadName  ${handlerThread.name}") }
            true
        }

        Log.d(TAG, "current Thread ${Thread.currentThread().name}")
        handler.sendEmptyMessage(100)
    }

    private fun practiceAsyncTaskTest() {

    }

    private fun practiceIntentServiceTest() {


    }

    private fun practiceThreadTest() {
        for (i in 0..9) {
            val thread = Thread(Runnable {
                Log.d(TAG, "ThreadName : ${Thread.currentThread().name}")
            })
            if (i == 5) {
                Process.setThreadPriority(8)
            }
            if (i == 2) {
                thread.priority = 10
            }
            thread.start()
        }

    }

}