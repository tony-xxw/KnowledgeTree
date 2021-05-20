package com.wynne.other.coroutine

import android.util.Log
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.other.R
import kotlinx.android.synthetic.main.activity_coroutine_layout.*
import kotlinx.coroutines.*

class CoroutineActivity : BaseActivity() {

    override fun initView() {

        btnCoroutineScene1.setOnClickListener { startScene1() }
        btnCoroutineScene2.setOnClickListener { startScene2() }

    }

    override val layoutId: Int = R.layout.activity_coroutine_layout


    private fun startScene1() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.e("XXW", "coroutine is runnig")
            val request1 = request1()
            val request2 = request2(request1)
            val request3 = request3(request2)

            updateUI(request3)
        }
        Log.e("XXW", "coroutine has launched")
    }


    suspend fun request1(): String {
        delay(2 * 1000)
        Log.e("XXW", "request1 work on ${Thread.currentThread().name}")
        return "result from request1"
    }

    suspend fun request2(result: String): String {
        delay(2 * 1000)
        Log.e("XXW", "request2 work on ${Thread.currentThread().name}")
        return "result from request2"
    }

    suspend fun request3(result: String): String {
        delay(2 * 1000)
        Log.e("XXW", "request3 work on ${Thread.currentThread().name}")
        return "result from request3"
    }

    fun startScene2() {
        GlobalScope.launch {
            val request1 = request1()
            val deferred2 = GlobalScope.async { request2(request1) }
            val deferred3 = GlobalScope.async { request3(request1) }
            updateUI(deferred2.await(), deferred3.await())
        }
    }

    private fun updateUI(request3: String) {
        Log.e("XXW", "updateui work on ${Thread.currentThread().name}")
        Log.e("XXW", "paramter: $request3")
    }

    private fun updateUI(request2: String, request3: String) {
        Log.e("XXW", "updateui work on ${Thread.currentThread().name}")
        Log.e("XXW", "paramter:  request2 $request2   request3 $request3")
    }
}