package com.wynne.android.handler

import android.os.Handler
import android.os.Message
import com.wynne.android.R
import com.wynne.android.databinding.ActiivtyBaseHandlerAndroidLayoutBinding
import com.wynne.knowledge.base.base.BaseActivity

class HandlerActivity : BaseActivity() {

    private val binding by lazy { ActiivtyBaseHandlerAndroidLayoutBinding.bind(root) }

    override fun initView() {


        handler.sendEmptyMessageDelayed(0, 20000)
        binding. btnFinish.setOnClickListener {
            finish()
        }

    }

    override val layoutId: Int = R.layout.actiivty_base_handler_android_layout

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            binding. btnFinish.text = "测试失败"
        }
    }
}