package com.wynne.android

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_ANDROID
import kotlinx.android.synthetic.main.actiivty_base_android_layout.*

@Route(path = BASE_ANDROID)
class BaseAndroidActivity : BaseActivity() {

    var list = mutableListOf<String>()
    override fun initView() {
        tlBar.title = "Android基础"

        list.add("flowLayout")
    }

    override fun getLayoutId(): Int = R.layout.actiivty_base_android_layout


    class adapter(conte) : RecyclerView.Adapter<viewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            var viewHolder = viewHolder(LayoutInflater.from(this).inflate(R.layout.item_layout))
        }

        override fun getItemCount(): Int {
        }

        override fun onBindViewHolder(holder: viewHolder, position: Int) {
        }


    }

    class viewHolder : RecyclerView.ViewHolder() {


    }
}
