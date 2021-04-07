package com.wynne.math

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_MATH
import com.wynne.math.linked.LinkedListActivity
import com.wynne.math.stack.StackActivity
import kotlinx.android.synthetic.main.activity_base_math_layout.*

@Route(path = BASE_MATH)
class BaseMathActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_base_math_layout

    override fun initView() {
        tlBar.title = "数据结构与算法"
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btnLinked -> {
                startActivity(Intent(this, LinkedListActivity::class.java))
            }
            R.id.btnStack -> {
                startActivity(Intent(this, StackActivity::class.java))
            }
        }
    }
}