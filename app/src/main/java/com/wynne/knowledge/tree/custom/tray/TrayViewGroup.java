package com.wynne.knowledge.tree.custom.tray;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author Wynne
 * @date 2018/3/22
 */

public class TrayViewGroup extends ViewGroup {

    public TrayViewGroup(Context context) {
        super(context);
    }

    public TrayViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            View childFirst = getChildAt(0);
            if (childFirst != null) {
                int childWidth = childFirst.getMeasuredWidth();
                int childHeight = childFirst.getMeasuredHeight();
                setMeasuredDimension(childWidth * getChildCount(), childHeight);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = 0;
        View child;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            child.layout(width, 0, child.getMeasuredWidth() + width, child.getMeasuredHeight());
            width += child.getMeasuredWidth();
        }
    }


}
