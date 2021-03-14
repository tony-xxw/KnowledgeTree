package com.wynne.android

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.android.lifecycle.LifeActivity
import com.wynne.android.third.TripartiteActivity
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_ANDROID
import kotlinx.android.synthetic.main.actiivty_base_android_layout.*

@Route(path = BASE_ANDROID)
class BaseAndroidActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XXW","onCreate: BaseAndroidActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("XXW","onStart: BaseAndroidActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("XXW","onResume: BaseAndroidActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("XXW","onRestart: BaseAndroidActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW","onPause: BaseAndroidActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("XXW","onStop: BaseAndroidActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("XXW","onDestroy: BaseAndroidActivity")
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d("XXW","onSaveInstanceState: BaseAndroidActivity")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("XXW","onRestoreInstanceState: BaseAndroidActivity")
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Log.d("XXW","onConfigurationChanged: BaseAndroidActivity")
    }

    lateinit var adapter: MainAdapter
    var list = mutableListOf(
            MainData("四大组件,生命周期启动方式,LifeCycle库", R.drawable.icon_grape),
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

        adapter.listener = {
            when (list[it].name) {
                "四大组件,生命周期启动方式,LifeCycle库" -> {
                    startActivity(Intent(this, LifeActivity::class.java))
                }
                "第三方库" -> {
                    startActivity(Intent(this, TripartiteActivity::class.java))
                }
            }

        }


        val decodeResource = BitmapFactory.decodeResource(resources, R.drawable.bg_advertisement)
        Log.d("XXW", "size: ${decodeResource.allocationByteCount}")
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
        val decodeResource1 = BitmapFactory.decodeResource(resources, R.drawable.bg_advertisement, options)
        Log.d("XXW", "size: ${decodeResource1.allocationByteCount}")
        val options1 = BitmapFactory.Options()
        options1.inPreferredConfig = Bitmap.Config.RGB_565
        options1.inSampleSize = 2
        val decodeResource2 = BitmapFactory.decodeResource(resources, R.drawable.bg_advertisement, options1)
        Log.d("XXW", "size: ${decodeResource2.allocationByteCount}")
    }


    override val layoutId: Int = R.layout.actiivty_base_android_layout


}
