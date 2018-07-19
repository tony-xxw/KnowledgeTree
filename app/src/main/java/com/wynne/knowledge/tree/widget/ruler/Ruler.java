package com.wynne.knowledge.tree.widget.ruler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/7/17
 */

public class Ruler extends ViewGroup {
    /**
     * 游标宽度
     */
    private int cursorWidth = 8;
    /**
     * 游标长度
     */
    private int cursorHeight = 70;
    /**
     * 游标图片
     */
    private Drawable cursorDrawble;
    /**
     * 当前游标
     */
    private int currentScale;


    public Ruler(Context context) {
        super(context);
    }

    public Ruler(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Ruler);

        typedArray.recycle();
    }


    public Ruler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
