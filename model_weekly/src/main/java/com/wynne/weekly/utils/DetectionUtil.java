package com.wynne.weekly.utils;

import android.util.Log;

import java.util.Stack;

/**
 * 监听耗时
 */
public class DetectionUtil {
    private static volatile DetectionUtil INSTANCE;
    private static final String TAG = "CUSTOM_TRANSFORM";
    private Stack<DetectionMethod> stack;
    private StringBuilder format;


    public static DetectionUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (DetectionUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetectionUtil();
                }
            }
        }
        return INSTANCE;
    }

    private DetectionUtil() {
        stack = new Stack<>();
        format = new StringBuilder();
    }

    public void startMethod(DetectionMethod method) {
        Log.e(TAG, String.format("%s[start]%s", format.toString(), method.getMethonName()));
        format.append("|    ");
        stack.push(method);
    }

    public void endMethod() {
        int length = format.length();
        format.delete(length - 4, length);
        Log.e(TAG, String.format("%s[end]%s", format.toString(), stack.pop().getOffsetTime()));
    }
}
