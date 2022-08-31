package com.wynne.kotlin

import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.utils.LogUtil
import com.wynne.kotlin.databinding.ActivtyMainBinding

class MainActivity : BaseActivity() {


    val binding: ActivtyMainBinding by lazy { ActivtyMainBinding.bind(root) }

    override fun initView() {

        binding.btnViewBind.setOnClickListener {
            LogUtil.d(javaClass.name, "ViewBinding!!!!!")
        }
    }

    override val layoutId: Int = R.layout.activty_main


    fun onClick(v: View) {
        when (v.id) {
            R.id.btnCoroutineBase -> {
                coroutineBase2()
            }
        }
    }
}