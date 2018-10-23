package com.wynne.knowledge.tree.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.wynne.knowledge.tree.custom.animator.Point;

/**
 * @author Wynne
 * @date 2018/10/24
 */

public class ObjectImageView extends AppCompatImageView {
    public ObjectImageView(Context context) {
        super(context);
    }

    public ObjectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObjectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setFallingPos(Point pos) {
        layout(pos.x, pos.y, pos.x + getWidth(), pos.y + getHeight());
    }
}
