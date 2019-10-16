package com.wynne.system

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_SYSTEM
import kotlinx.android.synthetic.main.activity_base_framwork_layout.*

@Route(path = BASE_SYSTEM)
class BaseSystemActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_framwork_layout

    override fun initView() {
        tlBar.title = "操作系统"
    }
}