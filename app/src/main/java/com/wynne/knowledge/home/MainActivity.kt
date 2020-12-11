package com.wynne.knowledge.home

import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 公司测试github With gitlab
 * @author Wynne
 */
class MainActivity : BaseActivity() {


    lateinit var adapter: MainAdapter
    var list = mutableListOf(
            MainData("Android基础", R.drawable.icon_apple),
            MainData("Android进阶", R.drawable.icon_watermelon),
            MainData("Java基础", R.drawable.icon_orange),
            MainData("并发/异步", R.drawable.icon_grape),
            MainData("设计模式", R.drawable.icon_peach),
            MainData("数据结构与算法", R.drawable.icon_pear),
            MainData("网络基础", R.drawable.icon_plum),
            MainData("操作系统", R.drawable.icon_tomato),
            MainData("Other", R.drawable.icon_lemon),
            MainData("每周一题", R.drawable.icon_apple))

    override fun initView() {
        toolBar.title = "知识体系"
        adapter = MainAdapter(this)
        adapter.mList = list
        adapter.listener = {
            when (it) {
                0 -> {
                    ARouter.getInstance().build(BASE_ANDROID).navigation()
                }
                1 -> {
                    ARouter.getInstance().build(BASE_HIGH).navigation()
                }
                2 -> {
                    ARouter.getInstance().build(BASE_JAVA).navigation()
                }
                3 -> {
                    ARouter.getInstance().build(BASE_THREAD).navigation()
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
                    ARouter.getInstance().build(BASE_SYSTEM).navigation()
                }
                8 -> {
                    ARouter.getInstance().build(BASE_OTHER).navigation()
                }
                9 -> {
                    ARouter.getInstance().build(BASE_WEEKLY).navigation()
                }
            }
        }
        rvMain.adapter = adapter


    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


}
