package com.wynne.android

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.android.fragment.AndroidFragmentActivity
import com.wynne.android.handler.HandlerActivity
import com.wynne.android.lifecycle.FourComponentWithLifeActivity
import com.wynne.android.third.TripartiteActivity
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_ANDROID
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.bannerview.holder.ViewHolder
import kotlinx.android.synthetic.main.actiivty_base_android_layout.*

@Route(path = BASE_ANDROID)
class BaseAndroidActivity : BaseActivity() {

    var str: String? = null


    lateinit var adapter: MainAdapter
    private var list = mutableListOf<MainData>(
            MainData("四大组件,生命周期启动方式", R.drawable.icon_grape),
            MainData("Fragment", R.drawable.icon_maize),
            MainData("自定义View", R.drawable.icon_apple),
            MainData("Handler的使用和消息队列源码", R.drawable.icon_watermelon),
            MainData("动画和手势", R.drawable.icon_peach),
            MainData("图片加载", R.drawable.icon_pear),
            MainData("文件和数据库", R.drawable.icon_plum),
            MainData("Resources", R.drawable.icon_tomato),
            MainData("第三方库", R.drawable.icon_lemon),
            MainData("Architecture components", R.drawable.icon_pepper),
            MainData("性能优化", R.drawable.icon_strawberry),
            MainData("单元测试", R.drawable.icon_orange),
            MainData("ANR", R.drawable.icon_orange))


    private lateinit var mViewPager: BannerViewPager<Int, AnnounceViewHolder>

    override fun initView() {
        adapter = MainAdapter(this)
        adapter.mList = list

        rvAndroid.adapter = adapter

        adapter.listener = {
            when (list[it].name) {
                "四大组件,生命周期启动方式" -> {
                    startActivity(Intent(this, FourComponentWithLifeActivity::class.java))
                }
                "第三方库" -> {
                    startActivity(Intent(this, TripartiteActivity::class.java))
                }
                "Fragment" -> {
                    startActivity(Intent(this, AndroidFragmentActivity::class.java))
                }
                "Handler的使用和消息队列源码" -> {
                    startActivity(Intent(this, HandlerActivity::class.java))
                }
                "ANR" -> {
                    startActivity(Intent(this, HandlerActivity::class.java))
                }
            }
        }
    }

    fun testBitmap() {

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


    fun initBanner() {
        val listOf = listOf(R.drawable.bg_advertisement, R.drawable.sapmle2)

        mViewPager = findViewById(R.id.bannerView)
        mViewPager.setCanLoop(true)
                .setAutoPlay(true)
                .setIndicatorMargin(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.design_fab_size_mini))
                .setIndicatorGravity(IndicatorGravity.CENTER)
                .setIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary),
                        ContextCompat.getColor(this, R.color.colorAccent))
                .setInterval(2000)
                .setScrollDuration(1000)
                .setHolderCreator { AnnounceViewHolder() }
                .setOnPageChangeListener(
                        object : OnPageChangeListenerAdapter() {
                            override fun onPageSelected(position: Int) {
                            }
                        }
                )
                .create(listOf)
    }


    override val layoutId: Int = R.layout.actiivty_base_android_layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XXW", "onCreate: BaseAndroidActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("XXW", "onStart: BaseAndroidActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("XXW", "onResume: BaseAndroidActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("XXW", "onRestart: BaseAndroidActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW", "onPause: BaseAndroidActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("XXW", "onStop: BaseAndroidActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("XXW", "onDestroy: BaseAndroidActivity")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("XXW", "onSaveInstanceState: BaseAndroidActivity")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("XXW", "onRestoreInstanceState: BaseAndroidActivity")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("XXW", "onConfigurationChanged: BaseAndroidActivity")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("XXW", "onNewIntent: BaseAndroidActivity")
    }
}


public class AnnounceViewHolder : ViewHolder<Int> {
    override fun getLayoutId(): Int = R.layout.item_banner

    override fun onBind(itemView: View, data: Int, position: Int, size: Int) {
        val imageView = itemView.findViewById<View>(R.id.ivBanner) as ImageView
        imageView.setBackgroundResource(data)
    }

}
