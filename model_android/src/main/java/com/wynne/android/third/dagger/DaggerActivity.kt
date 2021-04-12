package com.wynne.android.third.dagger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.wynne.android.R
import com.wynne.android.third.TripartiteActivity
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.actiivty_dagger_layout.*
import javax.inject.Inject
import javax.inject.Named

class DaggerActivity : BaseActivity() {

    @JvmField
    @Inject
    var testValue: Int = 0

    @JvmField
    @Named("phone")
    @Inject
    var phone: String = ""

    @JvmField
    @Named("number")
    @Inject
    var number: String = ""


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
            Log.d("XXW", "phone $phone  number $number")

            Toast.makeText(this, "注入值为 $testValue", Toast.LENGTH_LONG).show()
        }


        btnSingleton.setOnClickListener {
            startActivity(Intent(this, TripartiteActivity::class.java))
//            startActivity(Intent(this, DaggerSecondActivity::class.java))
        }

    }


    override val layoutId: Int= R.layout.actiivty_dagger_layout

}