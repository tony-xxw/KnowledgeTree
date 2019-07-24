package com.wynne.java.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.java.R
import com.wynne.knowledge.base.base.BaseActivity

import com.wynne.knowledge.base.constant.ARouterPath.BASE_JAVA
import kotlinx.android.synthetic.main.activity_base_java_layout.*

@Route(path = BASE_JAVA)
class BaseJavaActivity : BaseActivity() {
    override fun initView() {
        toolBar.title = "Java基础"
    }

    override fun getLayoutId(): Int = R.layout.activity_base_java_layout
}
