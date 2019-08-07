package com.wynne.android.recycle

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.utils.Constant.*
import kotlinx.android.synthetic.main.activity_base_recycle_layout.*

class RecycleViewActivity : BaseActivity() {

    var ary = mutableListOf(
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round)

    override fun initView() {
        val adapter = RecycleViewAdapter(ary, this)
        rvRecycle.layoutManager = GridLayoutManager(this, 2)
        rvRecycle.adapter = adapter

    }

    override fun getLayoutId(): Int = R.layout.activity_base_recycle_layout


    class RecycleViewAdapter(list: List<Int>, context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
        var list: List<Int>
        var mContext: Context
        var ary = mutableListOf(
                "第一个", "第一个", "第一个", "第一个", "第一个", "第一个")


        init {
            this.list = list
            mContext = context
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            if (viewType == ITEM_HEADER) {
                return BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_head_layout, parent, false))
            } else if (viewType == ITEM_FOOT) {
                return BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_foot_layout, parent, false))
            } else {
                return BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_layout, parent, false))
            }
        }

        override fun getItemCount(): Int = list.size + 2

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            if (holder.ivPhoto != null && getItemViewType(position) != ITEM_FOOT && getItemViewType(position) != ITEM_HEADER) {
                Log.d("XXW", "position: " + position)
                holder.ivPhoto.setImageResource(list[position - 1])
            }

            if (holder.rvHead != null) {
                holder.rvHead.layoutManager = LinearLayoutManager(mContext, LinearLayout.HORIZONTAL, false)
                holder.rvHead.adapter = RecycleTextAdapter(ary)
            }
        }

        override fun getItemViewType(position: Int): Int {
            when (position) {
                0 -> {
                    return ITEM_HEADER
                }
                itemCount - 1 -> {
                    return ITEM_FOOT
                }
                else -> {
                    return ITEM_NORMAL
                }
            }
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            if (recyclerView.layoutManager is GridLayoutManager) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        Log.d("XXW", "layoutManager.spanCount : " + layoutManager.spanCount)
                        Log.d("XXW", "getItemViewType(position)  : " + getItemViewType(position))
                        when (getItemViewType(position)) {
                            ITEM_HEADER -> {
                                return layoutManager.spanCount
                            }
                            ITEM_FOOT -> {
                                return layoutManager.spanCount
                            }
                            else -> {
                                return layoutManager.spanCount / 2

                            }
                        }
                    }
                }
            }
        }
    }


    class BaseViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var ivPhoto = item.findViewById<ImageView>(R.id.ivPhoto)
        var rvHead = item.findViewById<RecyclerView>(R.id.rvHeadList)
    }


    class RecycleTextAdapter(list: List<String>) : RecyclerView.Adapter<TextViewHolder>() {
        var list: List<String>

        init {
            this.list = list
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
            return TextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text_layout, parent, false))
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
            holder.tvText.text = list[position]
        }


    }


    class TextViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var tvText = item.findViewById<TextView>(R.id.tvText)
    }
}