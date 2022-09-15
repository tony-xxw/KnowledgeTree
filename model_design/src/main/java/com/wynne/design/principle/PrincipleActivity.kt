package com.wynne.design.principle

import android.content.Intent
import com.wynne.design.R
import com.wynne.knowledge.base.adapter.DefaultAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.bindAdapter
import kotlinx.android.synthetic.main.activity_principle_layout.*

class PrincipleActivity : BaseActivity() {

    private var list = mutableListOf(
        MainData("对扩展开发,修改关闭", R.drawable.icon_apple),
        MainData("need analyse demo", R.drawable.icon_apple)
    )

    override fun initView() {
        rvPrinciple.bindAdapter(this, list) {
            when (list[it].name) {
                "对扩展开发,修改关闭" -> {
                    startActivity(Intent(this, PrincipleExtensionActivity::class.java))
                }
            }
        }

        controlReversal()
    }

    override val layoutId: Int = R.layout.activity_principle_layout

    private fun controlReversal() {
        JunitApplication.register(IOCSample())
        JunitApplication.list.forEach {
            it.run()
        }
    }


}