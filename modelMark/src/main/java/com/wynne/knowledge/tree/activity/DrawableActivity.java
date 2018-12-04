package com.wynne.knowledge.tree.bookmark.activity;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.ImageView;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

/**
 * @author Wynne
 * @date 2018/10/17
 */

public class DrawableActivity extends BaseActivity {
    boolean isOnClick;
    ImageView ivLevel;
    ImageView ivTransition;

    @Override
    public void initView() {
        ivLevel = findViewById(R.id.iv_level);
        ivTransition = findViewById(R.id.iv_transition);

        TransitionDrawable transitionDrawable = (TransitionDrawable) ivTransition.getDrawable();
        transitionDrawable.startTransition(1000);

    }

    @Override
    public int getLayoutId() {
        return R.layout.drawable_activity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_level:
                if (!isOnClick) {
                    ivLevel.setImageLevel(60);
                    isOnClick = true;
                } else {
                    ivLevel.setImageLevel(20);
                    isOnClick = false;
                }
                break;
            default:
                break;
        }
    }
}
