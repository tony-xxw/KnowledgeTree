package com.wynne.android.img

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import com.wynne.android.R
import com.wynne.android.databinding.ActivityImageLoaderLayoutBinding
import com.wynne.knowledge.base.base.BaseActivity

class ImageLoaderActivity : BaseActivity() {
    private val memory = Runtime.getRuntime().totalMemory() / 1024
    val cacheSize = memory / 80

    private val binding by lazy { ActivityImageLoaderLayoutBinding.bind(root) }

    override fun initView() {

        binding.btnPutLruCache.setOnClickListener {
            Log.d("XXW", "total: $cacheSize")
            lruCachePut()
        }
        lruCache = object : LruCache<String, Bitmap>(cacheSize.toInt()) {
            override fun sizeOf(key: String?, value: Bitmap): Int {
                Log.d("XXW", "每一个缓存对象的大小为: ${value.rowBytes * value.height / 1024}")
                return value.rowBytes * value.height / 1024
            }
        }

        binding.btnDeleteLruCache.setOnClickListener {
            lruCacheGet()
        }
    }

    lateinit var lruCache: LruCache<String, Bitmap>

    private fun lruCachePut() {
        lruCache.put("data", BitmapFactory.decodeResource(resources, R.drawable.icon_grape))
        lruCache.put("data1", BitmapFactory.decodeResource(resources, R.drawable.icon_apple))
        lruCache.put("data2", BitmapFactory.decodeResource(resources, R.drawable.icon_maize))
        lruCache.put("data3", BitmapFactory.decodeResource(resources, R.drawable.icon_orange))

        lruCache.get("data")

        lruCache.put("data4", BitmapFactory.decodeResource(resources, R.drawable.icon_lemon))
        Log.d("XXW", "maxSize: ${lruCache.maxSize()}   size: ${lruCache.size()}")
    }

    private fun lruCacheGet() {
        lruCache.get("data")
        Log.d("XXW", "maxSize: ${lruCache.maxSize()}   size: ${lruCache.size()}")
    }

    override val layoutId: Int = R.layout.activity_image_loader_layout
}