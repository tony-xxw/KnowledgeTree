package com.wynne.knowledge.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class CircleMenuLayout extends ViewGroup {
    /**
     * 圆形直径
     */
    private int mRadius;
    /**
     * child item默认尺寸
     */
    private static final float RADIO_DEFAULT_CHILD_DIMENSION = 1 / 4f;
    /**
     * 容器内边距,无视padding
     */
    private static final float FLOATRADIO_PADDING_LAYOUT = 1 / 12;
    /**
     * 内边距
     */
    private float mPadding;
    /**
     * 开始角度
     */
    private double mStartAngle = 0;
    /**
     * 菜单项文本
     */
    private String[] mItemTexts;
    /**
     * 菜单项图标
     */
    private int[] mItemImgs;
    /**
     * 菜单个数
     */
    private int mMenuItemCount;


    public CircleMenuLayout(Context context) {
        super(context);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
