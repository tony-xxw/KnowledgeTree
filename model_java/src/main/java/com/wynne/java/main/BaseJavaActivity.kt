package com.wynne.java.main


import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.java.R
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_JAVA
import kotlinx.android.synthetic.main.activity_base_java_layout.*
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.iterator
import kotlin.collections.mutableListOf
import kotlin.collections.set


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
                    hashSetTest()
                }
                "JVM相关" -> {

                }
            }
        }
    }

    private fun linkedHashMapMain() {
        var linkedMapOf = LinkedHashMap<Int, Int>(0, 0.75f, true)

        linkedMapOf[0] = 0
        linkedMapOf[1] = 1
        linkedMapOf[2] = 2
        linkedMapOf[3] = 3
        linkedMapOf[4] = 4
        linkedMapOf[5] = 5
        linkedMapOf[6] = 6

        linkedMapOf[0]
        linkedMapOf[1]


        var test: MutableMap.MutableEntry<Int, Int>? = null
        for (entry in linkedMapOf.entries) {
            test = entry
        }
        test?.let {
            Log.d("XXW", "key: ${test.key} - value :${test.value}")
        }


    }

    override val layoutId: Int = R.layout.activity_base_java_layout

    fun hashSetTest() {
        val hashTest = HashSet<String>()
        hashTest.add("Test")


        for (test in hashTest) {

        }
    }
}
