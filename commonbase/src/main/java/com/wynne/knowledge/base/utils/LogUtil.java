package com.wynne.knowledge.base.utils;

import android.util.Log;

import com.wynne.knowledge.base.BuildConfig;

public class LogUtil {
    private static final boolean isDebug = BuildConfig.DEBUG;

    public static void d(String content) {
        if (isDebug) {
            Log.d("XXW", "value: " + content);
        }
    }

}
