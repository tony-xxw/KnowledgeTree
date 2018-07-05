package com.wynne.knowledge.tree;

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
                break;
            case View.MeasureSpec.EXACTLY:
                result = specSize;
                result = Math.max(result, defaultSize);
                break;
        }
        return result;
    }


}
