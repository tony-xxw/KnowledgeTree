package com.wynne.advanced

import android.content.Intent
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.wynne.advanced.hot.HotRepairActivity
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath
import com.wynne.knowledge.base.constant.ARouterPath.BASE_HIGH
import com.wynne.knowledge.base.constant.ARouterPath.BASE_HIGH_AROUTER
import kotlinx.android.synthetic.main.activity_base_advanced_layout.*

@Route(path = BASE_HIGH)
class BaseAdvancedActivity : BaseActivity() {

    lateinit var adapter: MainAdapter

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
            MainData("wms,pms,systemServerw", R.drawable.icon_tomato),
            MainData("单元测试", R.drawable.icon_lemon))

    override fun initView() {
        toolBar.title = "Android高级/专家"
        adapter = MainAdapter(this)
        adapter.mList = list

        rvAdvanced.adapter = adapter

        adapter.listener = {
            when (list[it].name) {
                "组件化" -> {
                    ARouter.getInstance().build(BASE_HIGH_AROUTER).navigation(this)
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