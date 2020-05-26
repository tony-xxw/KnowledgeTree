package com.wynne.advanced.dagger

import android.widget.Toast
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_advanced_dagger_layout.*
import javax.inject.Inject

class DaggerActivity : BaseActivity() {




    override fun initView() {

        btn1.setOnClickListener {
            val platform = DaggerPlatform.builder().build().waimai()
            Toast.makeText(this, platform.eat(), Toast.LENGTH_SHORT).show()
        }

        btn2.setOnClickListener {
            val platform = DaggerTakeOutPlatform.builder().shangjiaModule(ShangjiaModule("衡阳鱼粉")).build()
            val person = Person1()
            platform.zhuru(person)
            platform.inject(this)


            Toast.makeText(this, platform.waimai().eat() + " test: ", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getLayoutId() = R.layout.activity_base_advanced_dagger_layout
}