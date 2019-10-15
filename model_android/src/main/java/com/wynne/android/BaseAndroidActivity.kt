package com.wynne.android

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_ANDROID
import kotlinx.android.synthetic.main.actiivty_base_android_layout.*

@Route(path = BASE_ANDROID)
class BaseAndroidActivity : BaseActivity() {

    lateinit var adapter: MainAdapter
    var list = mutableListOf(
            MainData("四大组件,生命周期启动方式", R.drawable.icon_grape),
            MainData("自定义View", R.drawable.icon_apple),
            MainData("Handler的使用和消息队列源码", R.drawable.icon_watermelon),
            MainData("动画和手势", R.drawable.icon_peach),
            MainData("图片加载", R.drawable.icon_pear),
            MainData("文件和数据库", R.drawable.icon_plum),
            MainData("Resources", R.drawable.icon_tomato),
            MainData("第三方库", R.drawable.icon_lemon),
            MainData("Architecture components", R.drawable.icon_lemon),
            MainData("性能优化", R.drawable.icon_lemon),
            MainData("单元测试", R.drawable.icon_lemon))

    override fun initView() {
        toolBar.title = "Android基础"
        adapter = MainAdapter(this)
        adapter.mList = list

        rvAndroid.adapter = adapter



    }
        override fun getLayoutId(): Int = R.layout.actiivty_base_android_layout


}
