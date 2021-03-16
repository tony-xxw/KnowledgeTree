package com.wynne.advanced.jetpack

import android.content.Intent
import android.view.View
import com.wynne.advanced.R
import com.wynne.advanced.jetpack.navigation.JetPackNavigationActivity
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_advanced_jetpack_layout.*

class JetPackActivity : BaseActivity() {
    override fun initView() {
        tlBar.title = "JetPack Demo"
    }

    override val layoutId: Int = R.layout.activity_base_advanced_jetpack_layout


    fun onNavigationClick(v: View) {
        startActivity(Intent(this, JetPackNavigationActivity::class.java))
    }

    fun onDataBindingClick(v: View) {

    }

    fun onViewModelWithLiveDataClick(v: View) {

    }

    fun onRoomClick(v: View) {

    }

    fun onPagingClick(v: View) {

    }

    fun onWorkManagerClick(v: View) {

    }
}