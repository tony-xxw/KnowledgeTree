package com.wynne.advanced.dagger

import android.widget.Toast
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import dagger.android.DaggerContentProvider
import kotlinx.android.synthetic.main.activity_base_advanced_dagger_layout.*

class DaggerActivity : BaseActivity() {
    override fun initView() {
        val platform = DaggerPlatform.builder().build().waimai()

        btn1.setOnClickListener {
            Toast.makeText(this, platform.eat(), Toast.LENGTH_LONG).show()
        }

    }

    override fun getLayoutId() = R.layout.activity_base_advanced_dagger_layout
}