package com.wynne.knowledge.tree.widget.thumb;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wynne on 2018/7/3.
 */

public class CountView extends View {
    public static final String DEFAULT_TEXT_COLOR = "#cccccc";

    public static final float DEFAULT_TEXT_SIZE = 15f;

    private int mTextColor;
    private int mEndTextColor;
    private float mTextSize;
    private int mCount;

    public CountView(Context context) {
        super(context);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
        calculateChangeNum(0);
        requestLayout();
    }

    public void calculateChangeNum(int i) {

    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mEndTextColor = Color.argb(0, Color.red(mTextColor), Color.green(mTextColor), Color.blue(mTextColor));
        postInvalidate();
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        requestLayout();
    }
}
