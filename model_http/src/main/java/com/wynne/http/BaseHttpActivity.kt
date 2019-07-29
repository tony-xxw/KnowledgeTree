package com.wynne.http

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath
import kotlinx.android.synthetic.main.activity_base_http_layout.*

@Route(path = ARouterPath.BASE_HTTP)
class BaseHttpActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_http_layout

    override fun initView() {
        tlBar.title = "网络基础"
    }
}