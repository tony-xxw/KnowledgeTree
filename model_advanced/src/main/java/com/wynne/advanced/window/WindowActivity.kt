package com.wynne.advanced.window

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.advanced.R
import kotlinx.android.synthetic.main.activty_base_advanced_window_layout.*

class WindowActivity : BaseActivity() {

    lateinit var handler: Handler
    override fun initView() {


        Log.d("XXW", Thread.currentThread().name)

        val thread = Thread {
            Looper.prepare()
            handler = object : Handler() {
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    Log.d("XXW", Thread.currentThread().name)
                }
            }
            Looper.loop()

        }
        thread.start()

        btnSendMsg.setOnClickListener {
            handler.sendEmptyMessage(100)
        }
    }


    override val layoutId: Int = R.layout.activty_base_advanced_window_layout
}