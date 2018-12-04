package com.wynne.knowledge.tree.tray;

import android.content.Context;
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
        CustomLayoutParams params;

        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            int childSize = getChildCount();
            int groupWidth = 0;
            for (int i = 0; i < childSize; i++) {
                params = (CustomLayoutParams) getChildAt(i).getLayoutParams();
                groupWidth += getChildAt(i).getMeasuredWidth() + params.leftMargin;
            }
            if (groupWidth != 0) {
                int childHeight = getChildAt(0).getMeasuredHeight();
                setMeasuredDimension(groupWidth, childHeight);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = 0;
        View child;
        CustomLayoutParams params;

        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            params = (CustomLayoutParams) child.getLayoutParams();
            Log.d("XXW", "leftMargin==" + params.leftMargin + "");
            child.layout(width, 0, child.getMeasuredWidth() + width, child.getMeasuredHeight());
            width += child.getMeasuredWidth() + params.leftMargin;
        }
    }


    /**
     * 生成默认的布局参数
     */
    @Override
    protected CustomLayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 生成布局参数 将布局参数包装成我们的
     */
    @Override
    protected android.view.ViewGroup.LayoutParams generateLayoutParams(
            android.view.ViewGroup.LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    /**
     * 生成布局参数 从属性配置中生成我们的布局参数
     */
    @Override
    public android.view.ViewGroup.LayoutParams generateLayoutParams(
            AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    /**
     * 检查当前布局参数是否是我们定义的类型这在code声明布局参数时常常用到
     */
    @Override
    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    /**
     * @author AigeStudio {@link http://blog.csdn.net/aigestudio}
     */
    public static class CustomLayoutParams extends MarginLayoutParams {

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }
    }

}
