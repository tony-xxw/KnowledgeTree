package com.wynne.knowledge.base.utils

import android.util.Log
import com.wynne.knowledge.base.BuildConfig

object LogUtil {
    private val isDebug = BuildConfig.DEBUG

    fun d(content: String) {
        if (isDebug) {
            Log.d("XXW", "value: $content")
        }
    }

    fun d(tag: String = "XXW", content: String) {
        if (isDebug) {
            Log.d("XXW", "$tag: $content")
        }
    }
}