package com.wynne.android.third.dagger

import android.widget.Toast
import com.wynne.android.R
import com.wynne.android.databinding.ActiivtyDaggerSecondLayoutBinding
import com.wynne.android.databinding.ActiivtyRetrofitLayoutBinding
import com.wynne.knowledge.base.base.BaseActivity
import javax.inject.Inject

class DaggerSecondActivity : BaseActivity() {

    @Inject
    @JvmField
    var testSingleton: TestSingleton? = null

    @Inject
    @JvmField
    var testSingleton1: TestSingleton? = null

    private val binding by lazy { ActiivtyDaggerSecondLayoutBinding.bind(root) }

    override fun initView() {
        DaggerActivityComponent.builder().build().inject(this)


        binding.btnSingle.setOnClickListener {
            Toast.makeText(this, "single ${testSingleton.toString()} singel1 ${testSingleton1.toString()}", Toast.LENGTH_SHORT).show()
        }


    }

    override val layoutId: Int = R.layout.actiivty_dagger_second_layout
}