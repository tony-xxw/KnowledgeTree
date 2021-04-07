package com.wynne.advanced

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.ActivityProvider
import com.wynne.knowledge.base.constant.ARouterPath.PROVIDER

@Route(path = PROVIDER)
class AdvanceProvider : ActivityProvider {
    override fun fetchActivity(): Activity {
        return BaseAdvancedActivity()
    }

    override fun init(context: Context?) {
        TODO("Not yet implemented")
    }
}