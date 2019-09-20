package com.wynne.knowledge.guide.material

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.guide.R

import java.util.Arrays

/**
 * @author Wynne
 * @date 2018/4/27
 */

class RecycleActivity : BaseActivity() {
    private val arrys = arrayOf("Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9", "Item10", "Item11", "Item12", "Item13", "Item14", "Item15", "Item16", "Item17", "Item18", "Item19", "Item20")
    private var mList: List<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mine_layout)
        initView()

    }

    override fun initView() {
        mList = Arrays.asList(*arrys)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = RecycleViewAdapter(this)
        recyclerView.adapter = adapter


    }


    override fun getLayoutId(): Int {
        return R.layout.mine_layout
    }


    inner class RecycleViewAdapter(private val mContext: Context) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            Log.d("XXW", "viewType :$viewType")
            val view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mTextView.text = mList!![position] + " === " + position
        }

        override fun getItemCount(): Int {
            return mList!!.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val mTextView: TextView

            init {
                mTextView = itemView.findViewById<View>(R.id.tv_item) as TextView
            }
        }


    }
}
