package com.wynne.android.third.dagger

import android.widget.Toast
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.actiivty_dagger_second_layout.*
import javax.inject.Inject

class DaggerSecondActivity : BaseActivity() {

    @Inject
    @JvmField
    var testSingleton: TestSingleton? = null

    @Inject
    @JvmField
    var testSingleton1: TestSingleton? = null

    override fun initView() {
        DaggerActivityComponent.builder().build().inject(this)


        btnSingle.setOnClickListener {
            Toast.makeText(this, "single ${testSingleton.toString()} singel1 ${testSingleton1.toString()}", Toast.LENGTH_SHORT).show()
        }


    }

    override fun getLayoutId(): Int = R.layout.actiivty_dagger_second_layout
}