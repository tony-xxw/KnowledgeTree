package com.wynne.knowledge.main;

import android.content.Context;
import android.util.Log;
import android.view.View;

/**
 * Created by Wynne on 2018/3/13.
 */

public class Utils {

    public static void d(String TAG, String content) {
        Log.d(TAG, "====" + content);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getDefaultSize(int measureSpec, int defaultSize) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case View.MeasureSpec.UNSPECIFIED:
            case View.MeasureSpec.AT_MOST:
                result = 200;
                break;
            case View.MeasureSpec.EXACTLY:
                result = specSize;
                result = Math.max(result, defaultSize);
                break;
            default:
                break;

        }
        return result;
    }

    public static Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        float startA = ((startInt >> 24) & 0xff) / 255.0f;
        float startR = ((startInt >> 16) & 0xff) / 255.0f;
        float startG = ((startInt >> 8) & 0xff) / 255.0f;
        float startB = (startInt & 0xff) / 255.0f;

        int endInt = (Integer) endValue;
        float endA = ((endInt >> 24) & 0xff) / 255.0f;
        float endR = ((endInt >> 16) & 0xff) / 255.0f;
        float endG = ((endInt >> 8) & 0xff) / 255.0f;
        float endB = (endInt & 0xff) / 255.0f;

        // convert from sRGB to linear
        startR = (float) Math.pow(startR, 2.2);
        startG = (float) Math.pow(startG, 2.2);
        startB = (float) Math.pow(startB, 2.2);

        endR = (float) Math.pow(endR, 2.2);
        endG = (float) Math.pow(endG, 2.2);
        endB = (float) Math.pow(endB, 2.2);

        // compute the interpolated color in linear space
        float a = startA + fraction * (endA - startA);
        float r = startR + fraction * (endR - startR);
        float g = startG + fraction * (endG - startG);
        float b = startB + fraction * (endB - startB);

        // convert back to sRGB in the [0..255] range
        a = a * 255.0f;
        r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f;
        g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f;
        b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;

        return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
    }

    private static float mFactorCache = 0;
    private static float mDividerCache = 1;


    public static String resultValueOf(float input, float factor) {
        if (factor > 1) {
            return String.valueOf(input * factor);
        } else if (factor > 0) {
            if (mFactorCache != factor) {
                mFactorCache = factor;
                mDividerCache = 1 / factor;
            }
            return String.valueOf(input / mDividerCache);
        } else {
            try {
                throw new Exception("Invalid factor!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
