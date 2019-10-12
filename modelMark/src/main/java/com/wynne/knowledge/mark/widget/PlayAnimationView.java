package com.wynne.knowledge.mark.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Wynne
 * @date 2018/10/25
 */

public class PlayAnimationView extends View {
    private Paint mPaint;
    private float mCurrentValue;
    private Path playPath;
    private Path despath;
    private int mRaido = 50;
    private int mCenterX = 100;
    private int mCenterY = 100;
    private PathMeasure mPathMeasure;

    public PlayAnimationView(Context context) {
        super(context);
        init();
    }

    public PlayAnimationView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayAnimationView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        despath = new Path();
        playPath = new Path();
        playPath.addCircle(mCenterX, mCenterY, mRaido, Path.Direction.CW);

        playPath.moveTo(mCenterX - mRaido / 2, mCenterY);
        playPath.lineTo(mCenterX, mCenterY + mRaido / 2);
        playPath.lineTo(mCenterX + mRaido / 2, mCenterY - mRaido / 3);


        mPathMeasure = new PathMeasure(playPath, false);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 2);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000).start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (mCurrentValue < 1) {
            float stop = mPathMeasure.getLength() * mCurrentValue;
            mPathMeasure.getSegment(0, stop, despath, true);

        } else if (mCurrentValue == 1) {
            mPathMeasure.getSegment(0, mPathMeasure.getLength(), despath, true);
            mPathMeasure.nextContour();
        } else {
            float stop = mPathMeasure.getLength() * (mCurrentValue - 1);
            mPathMeasure.getSegment(0, stop, despath, true);
        }

        canvas.drawPath(despath, mPaint);
    }
}
