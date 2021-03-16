package com.wynne.advanced.dagger

import android.content.Intent
import android.util.Log
import com.wynne.advanced.AdvancedApplication
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_advanced_dagger_layout.*
import javax.inject.Inject
import javax.inject.Named

class DaggerActivity : BaseActivity() {

    @Inject
    lateinit var cloth: Cloth

    @Inject
    @field:Named("blue")
    lateinit var blueCloth: Cloth

    @Inject
    @field:Named("red")
    lateinit var redCloth: Cloth

    @Inject
    lateinit var stringBuffer: StringBuffer

    @Inject
    lateinit var cloths: Cloths

    @Inject
    lateinit var clothHandler: ClothHandler

    override fun initView() {
//        DaggerMainComponent.builder().baseComponent(AdvancedApplication.getInstance().baseComponent).build().injenct(this)
        AdvancedApplication.getInstance().getBaseComponent().getCubMainComponent(MainModule()).inject(this)

        btn1.text = cloth.toString() + blueCloth.toString() + redCloth.toString()
        btn2.text = stringBuffer.toString()
        btn3.text = cloths.toString()


        btn4.text = "redCloth=clothes中的cloth吗?: ${redCloth == cloths.mCloth}"


        Log.d("xxw", "红布料加工后变成了 ${clothHandler.handle(redCloth)}")
        Log.d("xxw", "clothHandler地址 $clothHandler")

        btn5.setOnClickListener {
            startActivity(Intent(this, DaggerOneActivity::class.java))
        }
    }

    override val layoutId: Int = R.layout.activity_base_advanced_dagger_layout


}