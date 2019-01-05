package com.wynne.knowledge.mark.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.mark.R;

/**
 * @author Wynne
 * @date 2018/10/25
 */

public class CirclePathView extends View {
    private Paint mPaint;
    private Path circlePath;
    private PathMeasure mPathMeasure;
    private float mCurrentValue;
    private float[] pos = new float[2];
    private float[] tan = new float[2];


    private Bitmap mBitmap;
    private Matrix matrix;

    public CirclePathView(Context context) {
        super(context);
        initPaint();
    }

    public CirclePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CirclePathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arraw);
        matrix = new Matrix();
        mPaint = new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        circlePath = new Path();
        circlePath.addCircle(100, 100, 50, Path.Direction.CW);

        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(circlePath, true);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000).start();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        float stop = mPathMeasure.getLength() * mCurrentValue;
        circlePath.reset();

        mPathMeasure.getSegment(0, stop, circlePath, true);
        canvas.drawPath(circlePath, mPaint);

        /**
         *
         mPathMeasure.getPosTan(stop, pos, tan);
         float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
         Matrix matrix = new Matrix();
         matrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
         matrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
         */

        Matrix matrix = new Matrix();
        mPathMeasure.getMatrix(stop, matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        matrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, matrix, mPaint);



        /*canvas.drawColor(Color.WHITE);
        float lenght = mPathMeasure.getLength();
        float stop = lenght * mCurrentValue;
        float start = (float) (stop - (0.5 - (Math.abs(mCurrentValue - 0.5))) * lenght);
        circlePath.reset();

        mPathMeasure.getSegment(start, stop, circlePath, true);*/

        canvas.drawPath(circlePath, mPaint);


    }
}
