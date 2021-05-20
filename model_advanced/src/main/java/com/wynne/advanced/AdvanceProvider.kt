package com.wynne.advanced

import android.app.Activity
import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.ActivityProvider
import com.wynne.knowledge.base.constant.ARouterPath.PROVIDER

@Route(path = PROVIDER)
class AdvanceProvider : ActivityProvider {
    override fun fetchActivity(): Activity {
        Log.d("XXW", "fetchActivity")
        return BaseAdvancedActivity()
    }

    override fun init(context: Context?) {
        Log.d("XXW", "init")
    }
}