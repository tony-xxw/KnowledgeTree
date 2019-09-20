package com.wynne.knowledge.home.widget;

import android.content.Context;
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

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }
}
