package com.wynne.advanced

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath
import kotlinx.android.synthetic.main.activity_base_advanced_layout.*

@Route(path = ARouterPath.BASE_ADVANCED)
class BaseAdvancedActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_advanced_layout

    override fun initView() {
        tlBar.title = "Android进阶"
    }
}