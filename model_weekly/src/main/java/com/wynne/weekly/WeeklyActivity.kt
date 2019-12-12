package com.wynne.weekly

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_WEEKLY

@Route(path = BASE_WEEKLY)
class WeeklyActivity : BaseActivity() {
    override fun initView() {

    }

    override fun getLayoutId(): Int = R.layout.activity_weekly_main_layout
}