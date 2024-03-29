package com.wynne.design

import android.app.Activity
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.design.principle.PrincipleActivity
import com.wynne.knowledge.base.adapter.DefaultAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.bindAdapter
import com.wynne.knowledge.base.constant.ARouterPath.BASE_DESIGN
import kotlinx.android.synthetic.main.activity_base_design_layout.*

@Route(path = BASE_DESIGN)
class BaseDesignActivity : BaseActivity() {
    lateinit var adapter: DefaultAdapter
    private var list = mutableListOf(
        MainData("面向对象六大原则", R.drawable.icon_apple),
        MainData("工厂模式(简单工厂模式,工厂方法模式,抽象工厂模式)", R.drawable.icon_watermelon),
        MainData("代理模式", R.drawable.icon_orange),
        MainData("装饰模式", R.drawable.icon_grape),
        MainData("观察者模式", R.drawable.icon_peach),
        MainData("适配器模式", R.drawable.icon_pear),
        MainData("策略模式", R.drawable.icon_plum),
        MainData("组合模式", R.drawable.icon_tomato),
        MainData("桥接模式", R.drawable.icon_lemon)
    )


    override fun initView() {
        rvDesign.bindAdapter(this, list) {
            when (list[it].name) {
                "面向对象六大原则" -> {
                    startActivity1<PrincipleActivity>()
                }
            }
        }
    }

    override val layoutId: Int = R.layout.activity_base_design_layout


    private inline fun <reified T : BaseActivity> Activity.startActivity1() {
        startActivity(Intent(this, T::class.java))
    }
}