package com.wynne.design

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_DESIGN
import kotlinx.android.synthetic.main.activity_base_design_layout.*

@Route(path = BASE_DESIGN)
class BaseDesignActivity : BaseActivity() {
    lateinit var adapter: MainAdapter
    var list = mutableListOf(
            MainData("单例模式", R.drawable.icon_apple),
            MainData("工厂模式(简单工厂模式,工厂方法模式,抽象工厂模式)", R.drawable.icon_watermelon),
            MainData("代理模式", R.drawable.icon_orange),
            MainData("装饰模式", R.drawable.icon_grape),
            MainData("观察者模式", R.drawable.icon_peach),
            MainData("适配器模式", R.drawable.icon_pear),
            MainData("策略模式", R.drawable.icon_plum),
            MainData("组合模式", R.drawable.icon_tomato),
            MainData("桥接模式", R.drawable.icon_lemon),
            MainData("职责原则", R.drawable.icon_orange),
            MainData("职责链模式", R.drawable.icon_strawberry),
            MainData("状态模式", R.drawable.icon_pepper),
            MainData("封闭原则", R.drawable.icon_pomelo),
            MainData("迪米特法则", R.drawable.icon_grape),
            MainData("中介者模式", R.drawable.icon_peach),
            MainData("外观模式", R.drawable.icon_pear),
            MainData("迭代器模式", R.drawable.icon_plum),
            MainData("访问者模式", R.drawable.icon_tomato),
            MainData("解释器模式", R.drawable.icon_lemon),
            MainData("享元模式", R.drawable.icon_orange),
            MainData("命令模式", R.drawable.icon_pear),
            MainData("模板方法模式", R.drawable.icon_plum),
            MainData("备忘录模式", R.drawable.icon_tomato),
            MainData("原型模式", R.drawable.icon_lemon),
            MainData("依赖倒转原则", R.drawable.icon_orange)
    )

    override fun getLayoutId(): Int = R.layout.activity_base_design_layout

    override fun initView() {
        toolBar.title = "设计模式"
        adapter = MainAdapter(this)
        adapter.mList = list

        rvDesign.adapter = adapter
    }
}