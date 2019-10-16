package com.wynne.other

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath
import kotlinx.android.synthetic.main.activity_base_other_layout.*

@Route(path = ARouterPath.BASE_OTHER)
class BaseOtherActivity : BaseActivity() {
    override fun initView() {
        toolBar.title = "其他"
    }

    override fun getLayoutId(): Int = R.layout.activity_base_other_layout
}