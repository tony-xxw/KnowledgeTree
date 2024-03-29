package com.wynne.advanced

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wynne.advanced.window.WindowActivity
import com.wynne.advanced.hot.HotRepairActivity
import com.wynne.knowledge.base.adapter.DefaultAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.bindAdapter
import com.wynne.knowledge.base.constant.ARouterPath.BASE_HIGH
import com.wynne.knowledge.base.constant.ARouterPath.BASE_HIGH_AROUTER
import kotlinx.android.synthetic.main.activity_base_advanced_layout.*

@Route(path = BASE_HIGH)
class BaseAdvancedActivity : BaseActivity() {

    lateinit var adapter: DefaultAdapter

    var list = mutableListOf(
        MainData("Jetpack", R.drawable.icon_grape),
        MainData("RemoteViews", R.drawable.icon_apple),
        MainData("Binder、AIDL、多进程", R.drawable.icon_watermelon),
        MainData("四大组件源码", R.drawable.icon_peach),
        MainData("JNI和JDK", R.drawable.icon_pear),
        MainData("Gradle", R.drawable.icon_plum),
        MainData("框架原理", R.drawable.icon_tomato),
        MainData("插件化和热修复", R.drawable.icon_peanut),
        MainData("JVM", R.drawable.icon_orange),
        MainData("MVP,MVC,MVVM", R.drawable.icon_pepper),
        MainData("组件化", R.drawable.icon_pomelo),
        MainData("安全", R.drawable.icon_strawberry),
        MainData("Window", R.drawable.icon_maize),
        MainData("wms,pms,systemServerw", R.drawable.icon_tomato),
        MainData("单元测试", R.drawable.icon_lemon)
    )

    override fun initView() {
        toolBar.title = "Android高级/专家"
        rvAdvanced.bindAdapter(this, list) {
            when (list[it].name) {
                "组件化" -> {
                    ARouter.getInstance().build(BASE_HIGH_AROUTER).navigation(this)
                }
                "Window" -> {
                    startActivity(Intent(this, WindowActivity::class.java))
                }
                "插件化和热修复" -> {
                    startActivity(Intent(this, HotRepairActivity::class.java))
                }
            }
        }
        Thread {
            toolBar.title = "111"
        }.start()
    }

    override val layoutId: Int = R.layout.activity_base_advanced_layout


}