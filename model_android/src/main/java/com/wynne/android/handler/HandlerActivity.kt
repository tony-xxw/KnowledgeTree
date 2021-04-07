package com.wynne.android.handler

import android.os.Handler
import android.os.Message
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.actiivty_base_handler_android_layout.*

class HandlerActivity : BaseActivity() {
    override fun initView() {


        handler.sendEmptyMessageDelayed(0, 20000)
        btnFinish.setOnClickListener {
            finish()
        }

    }

    override val layoutId: Int = R.layout.actiivty_base_handler_android_layout

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            btnFinish.text = "测试失败"
        }
    }
}