package com.wynne.advanced.dagger

import android.util.Log
import com.wynne.advanced.AdvancedApplication
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import javax.inject.Inject
import javax.inject.Named

class DaggerOneActivity : BaseActivity() {


    @Inject
    lateinit var clothHandler: ClothHandler

    @Inject
    @field:Named("blue")
    lateinit var blueCloth: Cloth

    override fun initView() {

        DaggerSecondComponent.builder().baseComponent(AdvancedApplication.getInstance().baseComponent).build().inject(this)

        Log.d("xxw", "蓝布料加工后变成了 ${clothHandler.handle(blueCloth)}")
        Log.d("xxw", "clothHandler地址 $clothHandler")
    }

    override fun getLayoutId(): Int = R.layout.activity_base_advanced_dagger1_layout
}