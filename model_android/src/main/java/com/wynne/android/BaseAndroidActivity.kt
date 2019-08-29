package com.wynne.android

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.android.R.id.rvList
import com.wynne.android.R.id.tlBar
import com.wynne.android.carousel.CarouselActivity
import com.wynne.android.custom.AndroidCustomActivity
import com.wynne.android.recycle.RecycleViewActivity
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_ANDROID
import com.wynne.knowledge.base.utils.Constant
import kotlinx.android.synthetic.main.actiivty_base_android_layout.*

@Route(path = BASE_ANDROID)
class BaseAndroidActivity : BaseActivity() {

    var list = mutableListOf<String>()
    override fun initView() {
        tlBar.title = "Android基础"

        list.add("流式布局")
        list.add("RecycleView")
        list.add("无限轮播ViewPager")
        rvList.layoutManager = LinearLayoutManager(this)
        val adapter = adapter(list)
        adapter.listener = {
            when (it) {
                0 -> {
                    startActivity(Intent(this, AndroidCustomActivity::class.java))
                }
                1 -> {
                    startActivity(Intent(this, RecycleViewActivity::class.java))
                }
                2 -> {
                    startActivity(Intent(this, CarouselActivity::class.java))
                }
            }

        }
        rvList.adapter = adapter
    }

    override fun getLayoutId(): Int = R.layout.actiivty_base_android_layout


    class adapter(list: List<String>) : RecyclerView.Adapter<viewHolder>() {
        var count = 0
        var list: List<String>
        lateinit var listener: (Int) -> Unit


        init {
            count = list.size
            this.list = list
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
        }

        override fun getItemCount(): Int = count

        override fun onBindViewHolder(holder: viewHolder, position: Int) {
            holder.textView.text = list[position]
            holder.textView.setOnClickListener {
                listener(position)
            }
        }


    }

    class viewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var textView = item.findViewById<TextView>(R.id.tvText)
    }
}
