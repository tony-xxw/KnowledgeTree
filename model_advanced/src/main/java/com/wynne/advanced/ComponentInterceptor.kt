package com.wynne.advanced

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.wynne.knowledge.base.constant.ARouterPath

@Interceptor(name = ARouterPath.INTERCEPTOR, priority = 2)
class ComponentInterceptor : IInterceptor {
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        postcard?.let {
            if (it.path.contains(ARouterPath.BASE_HIGH)) {
                callback?.onInterrupt(object : Throwable() {
                    override val message: String = "拦截"

                })
            }else{
                callback?.onContinue(postcard)
            }
        }
    }

    override fun init(context: Context?) {

    }
}