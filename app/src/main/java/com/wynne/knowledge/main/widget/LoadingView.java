package com.wynne.knowledge.main.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;

import com.wynne.knowledge.main.R;

/**
 * Created by Wynne on 2018/10/23.
 */

public class LoadingView extends AppCompatImageView {

    private int index = 0;

    private int loadingPhotoCount = 3;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    ValueAnimator animator;

    private void init() {
        animator = ValueAnimator.ofInt(0, 100, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                setTop(mTop - value);
                Log.d("XXW", "Top -value :  mTop  = " + mTop + " value = " + value);

            }
        });
        //无限循环
        animator.setRepeatCount(ValueAnimator.INFINITE);
        //倒序重新开始
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateInterpolator());

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                index++;

                switch (index % loadingPhotoCount) {
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_2));
                        break;
                    case 2:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_3));
                        break;
                    default:
                        break;
                }
            }
        });

        animator.start();
    }

    int mTop;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.cancel();
    }
}
