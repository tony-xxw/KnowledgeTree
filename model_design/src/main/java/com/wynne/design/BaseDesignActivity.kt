package com.wynne.design

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_DESIGN
import kotlinx.android.synthetic.main.activity_base_design_layout.*

@Route(path = BASE_DESIGN)
class BaseDesignActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_design_layout

    override fun initView() {
        tlBar.title = "设计模式"
    }
}