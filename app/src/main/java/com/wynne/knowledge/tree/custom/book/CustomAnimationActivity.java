package com.wynne.knowledge.tree.custom.book;

import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

/**
 * @author Wynne
 * @date 2018/10/22
 */

public class CustomAnimationActivity extends BaseActivity {
    @Override
    public void initView() {
        ImageView imageView = findViewById(R.id.iv_scale);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(6000);
        imageView.startAnimation(scaleAnimation);
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_animation_layout;
    }
}
