package com.wynne.android

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_ANDROID
import kotlinx.android.synthetic.main.actiivty_base_android_layout.*

@Route(path = BASE_ANDROID)
class BaseAndroidActivity:BaseActivity(){
    override fun initView() {
        tlBar.title="Android基础"
     }

    override fun getLayoutId(): Int  =R.layout.actiivty_base_android_layout

}
