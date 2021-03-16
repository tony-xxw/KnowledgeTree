package com.wynne.advanced.jetpack.navigation

import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_advanced_jetpack_navagation_layout.*

class JetPackNavigationActivity : BaseActivity() {
    override fun initView() {
        tlBar.title = "Navigation"


    }

    override val layoutId: Int = R.layout.activity_base_advanced_jetpack_navagation_layout

}