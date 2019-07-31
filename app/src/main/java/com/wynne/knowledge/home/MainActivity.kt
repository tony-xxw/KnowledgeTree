package com.wynne.knowledge.home

import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Wynne
 */
class MainActivity : BaseActivity() {



    lateinit var adapter: MainAdapter
    var list = mutableListOf(
            "Java基础",
            "并发/异步",
            "Android基础",
            "Android进阶",
            "设计模式",
            "数据结构与算法",
            "网络基础",
            "开源框架",
            "额外一",
            "额外二")

    override fun initView() {
        adapter = MainAdapter(this)
        adapter.mList = list
        adapter.listener = {
            when (it) {
                0 -> {
                    ARouter.getInstance().build(BASE_JAVA).navigation()
                }
                1 -> {
                    ARouter.getInstance().build(BASE_THREAD).navigation()
                }
                2 -> {
                    ARouter.getInstance().build(BASE_ANDROID).navigation()
                }
                3 -> {
                    ARouter.getInstance().build(BASE_HIGH).navigation()
                }
                4 -> {
                    ARouter.getInstance().build(BASE_DESIGN).navigation()
                }
                5 -> {
                    ARouter.getInstance().build(BASE_MATH).navigation()
                }
                6 -> {
                    ARouter.getInstance().build(BASE_HTTP).navigation()
                }
                7 -> {
                    ARouter.getInstance().build(BASE_FRAMWORK).navigation()
                }
                8 -> {
                    ARouter.getInstance().build(FRAGMENT_GUIDE).navigation()
                }
                9 -> {
                    ARouter.getInstance().build(FRAGMENT_BOOKMARK).navigation()
                }
            }
        }
        rvMain.adapter = adapter

        Thread(Test()).start()



    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    class Test : Runnable {
        override fun run() {
            try {
                Log.d("XXW", "111")
                Thread.sleep(4000)

            } finally {
                Log.d("XXW", "222")
            }

        }

    }

}
