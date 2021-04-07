package com.wynne.advanced.arouter

import android.view.LayoutInflater
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_HIGH_AROUTER
import kotlinx.android.synthetic.main.activity_base_advanced_arouter_layout.*

@Route(path = BASE_HIGH_AROUTER)
class ArouterActivity : BaseActivity() {
    override fun initView() {

        val from = LayoutInflater.from(this)
        from.inflate(R.layout.activity_test, flContent)

    }

    override val layoutId: Int = R.layout.activity_base_advanced_arouter_layout

}