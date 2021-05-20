package com.wynne.android.img

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image_loader_layout.*

class ImageLoaderActivity : BaseActivity() {
    val memory = Runtime.getRuntime().totalMemory() / 1024
    val cacheSize = memory / 8

    override fun initView() {

        btnLruCache.setOnClickListener {
            lruCacheMain()
        }
        lruCache = object : LruCache<String, Bitmap>(cacheSize.toInt()) {
            override fun sizeOf(key: String?, value: Bitmap): Int {
                Log.d("XXW", "每一个缓存对象的大小为: ${value.rowBytes * value.height / 1024}")
                return value.rowBytes * value.height / 1024
            }
        }
    }

    lateinit var lruCache: LruCache<String, Bitmap>

    private fun lruCacheMain() {
    }


    override val layoutId: Int = R.layout.activity_image_loader_layout
}