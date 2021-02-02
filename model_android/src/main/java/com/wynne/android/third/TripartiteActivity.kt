package com.wynne.android.third

import android.content.Intent
import com.wynne.android.R
import com.wynne.android.third.dagger.DaggerActivity
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.actiivty_tripartite_layout.*

class TripartiteActivity : BaseActivity() {
    private val tripartiteList = mutableListOf(MainData("Dagger2", 0))
    lateinit var adapter: MainAdapter

    override fun initView() {
        adapter = MainAdapter(this)
        adapter.mList = tripartiteList
        rvTripartite.adapter = adapter

        adapter.listener = {
            when (tripartiteList[it].name) {
                "Dagger2" -> {
                    startActivity(Intent(this, DaggerActivity::class.java))
                }
            }
        }

    }

    override fun getLayoutId(): Int = R.layout.actiivty_tripartite_layout
}