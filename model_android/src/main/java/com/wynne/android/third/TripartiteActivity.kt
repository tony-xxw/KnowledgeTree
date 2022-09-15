package com.wynne.android.third

import android.content.Intent
import com.wynne.android.R
import com.wynne.android.databinding.ActiivtyTripartiteLayoutBinding
import com.wynne.android.third.dagger.DaggerActivity
import com.wynne.android.third.retrofit.RetrofitActivity
import com.wynne.android.third.rxjava.RxJavaActivity
import com.wynne.knowledge.base.adapter.DefaultAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.bindAdapter

class TripartiteActivity : BaseActivity() {
    private val tripartiteList =
        mutableListOf(MainData("Dagger2", 0), MainData("Retrofit", 0), MainData("RxJava", 0))
    private val binding by lazy { ActiivtyTripartiteLayoutBinding.bind(root) }


    override fun initView() {
        binding.rvTripartite.bindAdapter(this, tripartiteList) {
            when (tripartiteList[it].name) {
                "Dagger2" -> {
                    startActivity(Intent(this, DaggerActivity::class.java))
                }
                "Retrofit" -> {
                    startActivity(Intent(this, RetrofitActivity::class.java))
                }
                "RxJava" -> {
                    startActivity(Intent(this, RxJavaActivity::class.java))
                }

            }
        }

    }

    override val layoutId: Int = R.layout.actiivty_tripartite_layout
}