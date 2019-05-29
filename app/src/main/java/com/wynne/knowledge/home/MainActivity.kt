package com.wynne.knowledge.home

import android.support.v7.widget.GridLayoutManager
import com.wynne.knowledge.base.base.BaseActivity
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
        rvMain.layoutManager = GridLayoutManager(this, 3)
        rvMain.adapter = adapter

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


}
