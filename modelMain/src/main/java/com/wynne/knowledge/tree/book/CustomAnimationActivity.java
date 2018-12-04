package com.wynne.knowledge.tree.book;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.animator.CharEvaluator;
import com.wynne.knowledge.tree.animator.Point;
import com.wynne.knowledge.tree.animator.PointEvaluator;
import com.wynne.knowledge.tree.base.BaseActivity;


/**
 * @author Wynne
 * @date 2018/10/22
 */

public class CustomAnimationActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvChange;
    private ImageView ivObject;
    private ImageView ivFrame;
    private boolean isMenuOpen = false;

    private Button mItem1, mItem2, mItem3, mItem4, mItem5;

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

        initMenu();

        findViewById(R.id.btn_keyframe).setOnClickListener(this);
        ivFrame = findViewById(R.id.iv_keyframe);
    }

    private void initMenu() {
        findViewById(R.id.btn_menu).setOnClickListener(this);
        mItem1 = findViewById(R.id.item1);
        mItem1.setOnClickListener(this);
        mItem2 = findViewById(R.id.item2);
        mItem3 = findViewById(R.id.item3);
        mItem4 = findViewById(R.id.item4);
        mItem5 = findViewById(R.id.item5);
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_animation_layout;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_char) {//因为ASCLL码 中A-Z 是数字 所有我们进行转换就可以 获取数字 然后进行计算 在转换回字符
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

        } else if (i == R.id.btn_object) {
            ObjectAnimator objectAnimator = ObjectAnimator.
                    ofObject(ivObject, "fallingPos", new PointEvaluator(), new Point(0, 0), new Point(500, 500));
            objectAnimator.setInterpolator(new AccelerateInterpolator());
            objectAnimator.setDuration(5000);
            objectAnimator.start();

        } else if (i == R.id.btn_menu) {
            if (!isMenuOpen) {
                //开始动画
                openMenuAnimation();
                isMenuOpen = true;
            } else {
                isMenuOpen = false;
                closeMenuAnimation();
            }

        } else if (i == R.id.item1) {
            Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();

        } else if (i == R.id.btn_keyframe) {
            obtainKeyFrame();

        } else {
        }
    }

    private void obtainKeyFrame() {
        Keyframe frame = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1f, 0);


        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation", frame, frame1, frame2, frame3, frame4
                , frame5, frame6, frame7, frame8, frame9, frame10);

        Keyframe scaleXFrame1 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleXFrame2 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleXFrame3 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXFrame4 = Keyframe.ofFloat(1f, 1);

        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofKeyframe("scaleX", scaleXFrame1, scaleXFrame2, scaleXFrame3, scaleXFrame4);


        Keyframe scaleYFrame1 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleYFrame2 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleYFrame3 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYFrame4 = Keyframe.ofFloat(1f, 1);

        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofKeyframe("scaleY", scaleYFrame1, scaleYFrame2, scaleYFrame3, scaleYFrame4);


        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ivFrame, valuesHolder, valuesHolder1, valuesHolder2);
        animator.setDuration(1000).start();
    }


    private void closeMenuAnimation() {
        doAnimationClose(mItem1, 0, 5, 500);
        doAnimationClose(mItem2, 1, 5, 500);
        doAnimationClose(mItem3, 2, 5, 500);
        doAnimationClose(mItem4, 3, 5, 500);
        doAnimationClose(mItem5, 4, 5, 500);
    }

    /**
     * @param mItem1
     * @param i      角标
     * @param i1     总数
     * @param i2     半径
     */
    private void doAnimationOpen(View mItem1, int i, int i1, int i2) {
        if (mItem1.getVisibility() != View.VISIBLE) {
            mItem1.setVisibility(View.VISIBLE);
        }

        double degree = Math.toRadians(90) / (i1 - 1) * i;
        int transtionX = -(int) (i2 * Math.sin(degree));
        int transtionY = -(int) (i2 * Math.cos(degree));

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.playTogether(
                ObjectAnimator.ofFloat(mItem1, "translationX", 0, transtionX),
                ObjectAnimator.ofFloat(mItem1, "translationY", 0, transtionY),
                ObjectAnimator.ofFloat(mItem1, "scaleX", 0, 1.0f),
                ObjectAnimator.ofFloat(mItem1, "scaleY", 0, 1.0f),
                ObjectAnimator.ofFloat(mItem1, "alpha", 0, 1));
        animationSet.setDuration(500).start();
    }

    private void openMenuAnimation() {
        doAnimationOpen(mItem1, 0, 5, 500);
        doAnimationOpen(mItem2, 1, 5, 500);
        doAnimationOpen(mItem3, 2, 5, 500);
        doAnimationOpen(mItem4, 3, 5, 500);
        doAnimationOpen(mItem5, 4, 5, 500);
    }

    /**
     * @param mItem1
     * @param i      角标
     * @param i1     总数
     * @param i2     半径
     */
    private void doAnimationClose(Button mItem1, int i, int i1, int i2) {
        if (mItem1.getVisibility() != View.VISIBLE) {
            mItem1.setVisibility(View.VISIBLE);
        }

//        double degree = Math.toRadians(90) / (i1 - 1) * i;
        double degree = Math.PI * i / ((i1 - 1) * 2);
        int transtionX = -(int) (i2 * Math.sin(degree));
        int transtionY = -(int) (i2 * Math.cos(degree));

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.playTogether(
                ObjectAnimator.ofFloat(mItem1, "translationX", transtionX, 0),
                ObjectAnimator.ofFloat(mItem1, "translationY", transtionY, 0),
                ObjectAnimator.ofFloat(mItem1, "scaleX", 1.0f, 0.1f),
                ObjectAnimator.ofFloat(mItem1, "scaleY", 1.0f, 0.1f),
                ObjectAnimator.ofFloat(mItem1, "alpha", 1, 0.1f));
        animationSet.setDuration(500).start();
    }
}
