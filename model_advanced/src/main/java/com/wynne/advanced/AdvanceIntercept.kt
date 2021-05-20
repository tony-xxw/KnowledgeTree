package com.wynne.advanced

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import retrofit2.http.Path

@Interceptor(priority = 1)
class AdvanceIntercept : IInterceptor {
    override fun init(context: Context?) {
        Log.d("XXW", "init")
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.d("XXW", "process : $postcard")
        Log.d("XXW", "process :path ${postcard?.path}")
    }
}