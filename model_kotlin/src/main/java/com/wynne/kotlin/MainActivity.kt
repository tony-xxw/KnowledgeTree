package com.wynne.kotlin

import android.view.View
import com.wynne.knowledge.base.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun initView() {

    }

    override val layoutId: Int = R.layout.activty_main


    fun onClick(v: View) {
        when (v.id) {
            R.id.btnCoroutine -> {
                start()
            }
        }
    }
}