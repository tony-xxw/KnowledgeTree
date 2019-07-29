package com.wynne.math

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_MATH
import kotlinx.android.synthetic.main.activity_base_math_layout.*

@Route(path = BASE_MATH)
class BaseMathActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_math_layout

    override fun initView() {
        tlBar.title = "数据结构与算法"
    }
}