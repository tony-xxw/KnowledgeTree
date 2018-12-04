package com.wynne.knowledge.main.book;

import android.graphics.drawable.Animatable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.widget.ImageView;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;

/**
 * @author Wynne
 * @date 2018/10/25
 */

public class AnimationPathActivity extends BaseActivity {
    @Override
    public void initView() {
        ImageView imageView = findViewById(R.id.iv_vector);
        AnimatedVectorDrawableCompat vectorDrawable = AnimatedVectorDrawableCompat.create(AnimationPathActivity.this, R.drawable.line_animated_svg);
        imageView.setImageDrawable(vectorDrawable);
        ((Animatable) imageView.getDrawable()).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.animation_path_activity;
    }
}
