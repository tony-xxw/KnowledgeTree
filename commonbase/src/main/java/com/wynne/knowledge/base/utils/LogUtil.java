package com.wynne.knowledge.base.utils;

import android.util.Log;

public class LogUtil {
    public static final boolean isDebug = true;

    public static void d(String content) {
        if (isDebug) {
            Log.d("XXW", content);
        }
    }


}
