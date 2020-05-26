package com.wynne.advanced.dagger

import com.wynne.advanced.R
import com.wynne.advanced.dagger.Cloth
import com.wynne.advanced.dagger.DaggerMainComponent
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_advanced_dagger_layout.*
import javax.inject.Inject
import javax.inject.Named

class DaggerActivity : BaseActivity() {

    @Inject
    lateinit var cloth: Cloth

    @Inject
    @field:Named("red")
    lateinit var blueCloth: Cloth

    @Inject
    @field:Named("blue")
    lateinit var redCloth: Cloth

    @Inject
    lateinit var stringBuffer: StringBuffer

    @Inject
    lateinit var cloths: Cloths

    override fun initView() {
        DaggerMainComponent.builder().build().injenct(this)
        btn1.text = cloth.toString() + blueCloth.toString() + redCloth.toString()
        btn2.text = stringBuffer.toString()
        btn3.text = cloths.toString()


    }

    override fun getLayoutId(): Int = R.layout.activity_base_advanced_dagger_layout

}