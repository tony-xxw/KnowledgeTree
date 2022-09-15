package com.wynne.thread

import android.content.Intent
import android.os.*
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.adapter.DefaultAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.bindAdapter
import com.wynne.knowledge.base.constant.ARouterPath.BASE_THREAD
import com.wynne.thread.count.CountDownLatchScene
import kotlinx.android.synthetic.main.activity_thread_android_layout.*

@Route(path = BASE_THREAD)
class ThreadAndroidActivity : BaseActivity() {

    lateinit var task: WynneAckTask

    companion object {
        val TAG: String = ThreadAndroidActivity::class.java.name
    }


    var list = mutableListOf(
        MainData("Thread", R.drawable.icon_pomelo),
        MainData("线程池", R.drawable.icon_grape),
        MainData("AsyncTask", R.drawable.icon_apple),
        MainData("IntentService", R.drawable.icon_watermelon),
        MainData("HandlerThread", R.drawable.icon_peach),
        MainData("多进程通信Binder,AIDL,多进程", R.drawable.icon_pear),
        MainData("CountDownLatchScene", R.drawable.icon_orange)
    )

    override fun initView() {
        toolBar.title = "知识体系"
        rvThread.bindAdapter(this, list) {
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
                "CountDownLatchScene" -> {
                    CountDownLatchScene.start()
                }
            }
        }


        Thread {
            Looper.prepare()
            Looper.loop()
        }.start()
        obtainHeight()
    }

    private fun obtainHeight() {

        rvThread.post {
            Log.d("XXW", "rv宽为1: ${rvThread.width}")
            Log.d("XXW", "rv高为1: ${rvThread.height}")
        }

        val viewTreeObserver = rvThread.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener {
            Log.d("XXW", "rv宽为2: ${rvThread.width}")
            Log.d("XXW", "rv高为2: ${rvThread.height}")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            Log.d("XXW", "rv宽为3: ${rvThread.width}")
            Log.d("XXW", "rv高为3: ${rvThread.height}")
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
        task = WynneAckTask()
        task.execute("abc")
    }

    private fun practiceIntentServiceTest() {
        val intent = Intent(this, IntentServiceImp::class.java)
        intent.putExtra("key", "Wynne")
        startService(intent)

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


    class WynneAckTask : AsyncTask<String, Int, Boolean>() {

        override fun onPreExecute() {

            super.onPreExecute()
            Log.d(TAG, "onPreExecute :${Thread.currentThread().name}")
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute :${Thread.currentThread().name}")
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            Log.d(TAG, "onProgressUpdate :${Thread.currentThread().name}  value $values")
        }

        override fun onCancelled() {
            super.onCancelled()
            Log.d(TAG, "onCancelled")
        }

        override fun onCancelled(result: Boolean?) {
            super.onCancelled(result)
            Log.d(TAG, "onCancelled :${result}")
        }

        override fun doInBackground(vararg params: String?): Boolean {
            publishProgress(111)
            Log.d(TAG, "doInBackground :${Thread.currentThread().name}")
            return true
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        if (task != null) {
            task.cancel(true)
        }
    }
}