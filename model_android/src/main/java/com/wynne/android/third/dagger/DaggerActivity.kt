package com.wynne.android.third.dagger

import android.content.Intent
import android.widget.Toast
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.actiivty_dagger_layout.*
import javax.inject.Inject

class DaggerActivity : BaseActivity() {

    @JvmField
    @Inject
    var testValue: Int = 0


    override fun initView() {
        val waimai = DaggerPlatform.builder().build().waimai()

        /**
         * Basic inject
         */
        btnBasic.setOnClickListener { Toast.makeText(this, waimai.eat(), Toast.LENGTH_LONG).show() }


        //内部内注入
        //DaggerFoo_Bar_FooComponent.builder().build()

        /**
         * Provider + Module
         */
//        val waimai1 = DaggerShopPlatform.builder().build().waimai()
        val waimai1 = DaggerShopPlatform.builder().shenzhenModule(ShenzhenModule("小向")).build().waimai()
        btnProvider.setOnClickListener {
            Toast.makeText(this, waimai1.eat(), Toast.LENGTH_LONG).show()
        }
        /**
         * 外部传入注入
         */
        var zhainan1 = Zhainan1()
        DaggerShopPlatform.builder().shenzhenModule(ShenzhenModule("小汶同学")).build().input(zhainan1)
        btnOutInput.setOnClickListener {
            Toast.makeText(this, zhainan1.eat(), Toast.LENGTH_LONG).show()
        }

        /**
         * Activity注入
         */
        DaggerShopPlatform.builder().shenzhenModule(ShenzhenModule("小汶同学")).build().inject(this)
        btnActivity.setOnClickListener {
            Toast.makeText(this, "注入值为 $testValue", Toast.LENGTH_LONG).show()
        }


        btnSingleton.setOnClickListener {
            startActivity(Intent(this, DaggerSecondActivity::class.java))
        }

    }


    override fun getLayoutId(): Int = R.layout.actiivty_dagger_layout

}