package com.wynne.other.kotlin

import android.util.Log
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.other.R
import kotlin.properties.Delegates

class KotlinCoreActivity : BaseActivity() {
    val num = intArrayOf(1, 2, 3)

    override fun initView() {
//        Chapter2().show()
        Chapter3().show()

    }

    override fun getLayoutId(): Int = R.layout.activity_kotlin_core_layout
}
