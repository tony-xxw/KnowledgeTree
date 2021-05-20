package com.wynne.java.main


import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.java.R
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity

import com.wynne.knowledge.base.constant.ARouterPath.BASE_JAVA
import kotlinx.android.synthetic.main.activity_base_java_layout.*
import java.util.LinkedHashMap
import java.util.concurrent.BlockingQueue


@Route(path = BASE_JAVA)
class BaseJavaActivity() : BaseActivity() {
    lateinit var adapter: MainAdapter

    private var list = mutableListOf(
            MainData("集合相关", R.drawable.icon_apple),
            MainData("JVM相关", R.drawable.icon_lemon))


    override fun initView() {
        adapter = MainAdapter(this)
        adapter.mList = list
        rvDesign.adapter = adapter

        adapter.listener = {
            when (list[it].name) {
                "集合相关" -> {
                    linkedHashMapMain()
                }
                "JVM相关" -> {

                }
            }
        }
    }

    private fun linkedHashMapMain() {
        val linkedMapOf = LinkedHashMap<Int, Int>(0, 0.75f, true)

        linkedMapOf[0] = 0
        linkedMapOf[1] = 1
        linkedMapOf[2] = 2
        linkedMapOf[3] = 3
        linkedMapOf[4] = 4
        linkedMapOf[5] = 5
        linkedMapOf[6] = 6

        linkedMapOf[1]
        linkedMapOf[2]
        linkedMapOf.forEach {
            Log.d("XXW", "Key: ${it.key} - value: ${it.value}")
        }

    }

    override val layoutId: Int = R.layout.activity_base_java_layout
}
