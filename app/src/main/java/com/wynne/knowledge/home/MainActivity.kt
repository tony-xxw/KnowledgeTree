package com.wynne.knowledge.home

import com.alibaba.android.arouter.launcher.ARouter
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_JAVA
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
            "网络基础")

    override fun initView() {
        adapter = MainAdapter(this)
        adapter.mList = list
        adapter.listener = {
            when (it) {
                0 -> {
                    ARouter.getInstance().build(BASE_JAVA).navigation()
                }
                1 -> {

                }
            }
        }
        rvMain.adapter = adapter

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


}
