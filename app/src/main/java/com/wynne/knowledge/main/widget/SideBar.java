package com.wynne.knowledge.main.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Wynne
 * @date 2018/9/25
 */

public class SideBar extends View {

    private Context mContext;

    public SideBar(Context context) {
        super(context);
        mContext = context;
    }

    public SideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }
}
