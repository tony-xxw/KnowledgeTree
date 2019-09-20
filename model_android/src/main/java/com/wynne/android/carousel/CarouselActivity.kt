package com.wynne.android.carousel

import android.content.Context
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import android.widget.Toast
import android.view.ViewGroup
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_base_carousel_layout.*


class CarouselActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        if (position > LAST) {
            isChange = true
            currentPoition = FRIST
        } else if (position < FRIST) {
            isChange = true
            currentPoition = LAST
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (ViewPager.SCROLL_STATE_IDLE == state) {
            if (isChange) {
                isChange = false
                vpPager.setCurrentItem(currentPoition, false)
            }
        }
    }

    lateinit var adapter: ImageAdapter
    var FRIST: Int = 0
    var LAST: Int = 0
    var currentPoition: Int = 0
    var isChange: Boolean = false
    val account = mutableListOf<Int>(R.drawable.sample3, R.drawable.sample1, R.drawable.sapmle2, R.drawable.sample3, R.drawable.sample1)
    lateinit var list: ArrayList<ImageView>

    override fun initView() {
        initImageViewList()
        adapter = ImageAdapter(this, list)
        vpPager.adapter = adapter
        vpPager.currentItem = 1
        vpPager.addOnPageChangeListener(this)
        FRIST = 1
        LAST = account.size - 2
    }

    fun initImageViewList() {
        list = ArrayList()
        for (i in account.indices) {
            val imageView = ImageView(this)
            imageView.setImageResource(account[i])
            list.add(imageView)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_base_carousel_layout


    inner class ImageAdapter(private val context: Context, images: ArrayList<ImageView>) : PagerAdapter() {
        private val images: List<ImageView>

        init {
            this.images = images
        }


        override fun getCount(): Int {
            return images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(images[position])
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = images[position]
            view.setOnClickListener {
                Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
            }
            val viewPager = container as ViewPager
            viewPager.addView(view)
            return images[position]
        }
    }

}

