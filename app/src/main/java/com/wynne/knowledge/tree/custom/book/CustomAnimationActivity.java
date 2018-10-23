package com.wynne.knowledge.tree.custom.book;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;
import com.wynne.knowledge.tree.custom.animator.CharEvaluator;
import com.wynne.knowledge.tree.custom.animator.Point;
import com.wynne.knowledge.tree.custom.animator.PointEvaluator;


/**
 * @author Wynne
 * @date 2018/10/22
 */

public class CustomAnimationActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvChange;
    private ImageView ivObject;

    @Override
    public void initView() {
        ImageView imageView = findViewById(R.id.iv_scale);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(6000);
        imageView.startAnimation(scaleAnimation);


        tvChange = findViewById(R.id.tv_change);
        ivObject = findViewById(R.id.iv_object);
        findViewById(R.id.btn_char).setOnClickListener(this);
        findViewById(R.id.btn_object).setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_animation_layout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_char:
                //因为ASCLL码 中A-Z 是数字 所有我们进行转换就可以 获取数字 然后进行计算 在转换回字符
                ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        char letter = (Character) animation.getAnimatedValue();
                        tvChange.setText(String.valueOf(letter));
                    }
                });

                valueAnimator.setDuration(10000);
                valueAnimator.setInterpolator(new AccelerateInterpolator());
                valueAnimator.start();
                break;
            case R.id.btn_object:
                ObjectAnimator objectAnimator = ObjectAnimator.
                        ofObject(ivObject, "fallingPos", new PointEvaluator(), new Point(0, 0), new Point(500, 500));
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                objectAnimator.setDuration(5000);
                objectAnimator.start();
                break;
            default:
                break;
        }
    }
}
