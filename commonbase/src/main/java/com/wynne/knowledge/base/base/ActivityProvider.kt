package com.wynne.knowledge.base.base

import android.app.Activity
import com.alibaba.android.arouter.facade.template.IProvider

interface ActivityProvider : IProvider {

    fun fetchActivity(): Activity
}