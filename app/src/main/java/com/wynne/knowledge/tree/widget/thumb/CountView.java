package com.wynne.knowledge.tree.widget.thumb;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.Utils;

/**
 * Created by Wynne on 2018/7/3.
 */

public class CountView extends View {
    public static final String DEFAULT_TEXT_COLOR = "#cccccc";
    private static final int COUNT_ANIM_DURING = 250;
    public static final float DEFAULT_TEXT_SIZE = 15f;

    private int mTextColor;
    private int mEndTextColor;
    private float mTextSize;
    /**
     * 初始文字
     */
    private int mCount;
    private Paint mTextPaint;
    /**
     * 每一个角标对应的文字
     */
    private String[] mTexts;
    /**
     * 每一个文字对应的角标
     */
    private TuvPoint[] mTextPoints;

    private float mMaxOffsetY;
    private float mMinOffsetY;

    private float mOldOffsetY;
    private float mNewOffsetY;
    private float mFraction;
    private boolean mCountToBigger;


    public CountView(Context context) {
        this(context, null);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.CountView);
        mCount = attrsArray.getInteger(R.styleable.CountView_count, 0);
        mTextColor = attrsArray.getColor(R.styleable.CountView_count_color, Color.parseColor(DEFAULT_TEXT_COLOR));
        mTextSize = attrsArray.getDimension(R.styleable.CountView_count_size, Utils.dip2px(context, 15f));
        attrsArray.recycle();
        init();
    }


    private void init() {
        mTexts = new String[3];
        mTextPoints = new TuvPoint[3];
        mTextPoints[0] = new TuvPoint();
        mTextPoints[1] = new TuvPoint();
        mTextPoints[2] = new TuvPoint();

        calculateChangeNum(0);

        mMinOffsetY = 0;
        mMaxOffsetY = mTextSize;

        mEndTextColor = Color.argb(0, Color.red(mTextColor), Color.green(mTextColor), Color.blue(mTextColor));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
        calculateChangeNum(0);
        requestLayout();
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mEndTextColor = Color.argb(0, Color.red(mTextColor), Color.green(mTextColor), Color.blue(mTextColor));
        postInvalidate();
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        requestLayout();
    }

    //计算文字变化
    public void calculateChangeNum(int change) {
        if (change == 0) {
            mTexts[0] = String.valueOf(mCount);
            mTexts[1] = "";
            mTexts[2] = "";
            return;
        }

        String oldNum = String.valueOf(mCount);
        String newNum = String.valueOf(mCount + change);

        for (int i = 0; i < oldNum.length(); i++) {
            char oldChar = oldNum.charAt(i);
            char newChar = newNum.charAt(i);

            if (oldChar != newChar) {
                mTexts[0] = i == 0 ? "" : newNum.substring(0, i);
                mTexts[1] = oldNum.substring(i);
                mTexts[2] = newNum.substring(i);
                break;
            }

        }

        mCount += change;
        startAnim(change > 0);
    }


    public void startAnim(boolean isToBigger) {
        mCountToBigger = isToBigger;
        ObjectAnimator textOffsetY = ObjectAnimator.ofFloat(this, "textOffsetY", mMinOffsetY, mCountToBigger ? mMaxOffsetY : -mMaxOffsetY);
        textOffsetY.setDuration(COUNT_ANIM_DURING);
        textOffsetY.start();
    }


    public void setTextOffsetY(float offsetY) {
        mOldOffsetY = offsetY;
        if (mCountToBigger) {
            mNewOffsetY = offsetY - mMaxOffsetY;
        } else {
            mNewOffsetY = mMaxOffsetY + offsetY;
        }

        mFraction = (mMaxOffsetY - Math.abs(mOldOffsetY) / (mMaxOffsetY - mMinOffsetY));
        calculateLocation();
        postInvalidate();
    }

    public float getTextOffsetY() {
        return mMinOffsetY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                Utils.getDefaultSize(widthMeasureSpec, getContentWidth() + getPaddingLeft() + getPaddingRight()),
                Utils.getDefaultSize(heightMeasureSpec, getContentHeight() + getPaddingTop() + getPaddingBottom())
        );
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateLocation();
    }

    private void calculateLocation() {
        String text = String.valueOf(mCount);
        float singleTextWidth = mTextPaint.measureText(text) / text.length();
        float unChangeWidth = singleTextWidth * mTexts[0].length();

        Paint.FontMetricsInt metricsInt = mTextPaint.getFontMetricsInt();
        float y = getPaddingTop() + (getContentHeight() - metricsInt.bottom - metricsInt.top) / 2;

        mTextPoints[0].x = getPaddingLeft();
        mTextPoints[1].x = getPaddingLeft() + unChangeWidth;
        mTextPoints[2].x = getPaddingLeft() + unChangeWidth;

        mTextPoints[0].y = y;
        mTextPoints[1].y = y - mOldOffsetY;
        mTextPoints[2].y = y - mNewOffsetY;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //不变的部分
        mTextPaint.setColor(mTextColor);
        canvas.drawText(String.valueOf(mTexts[0]), mTextPoints[0].x, mTextPoints[0].y, mTextPaint);

        //变化前部分
        mTextPaint.setColor((Integer) Utils.evaluate(mFraction, mEndTextColor, mTextColor));
        canvas.drawText(String.valueOf(mTexts[1]), mTextPoints[1].x, mTextPoints[1].y, mTextPaint);

        //变化后部分
        mTextPaint.setColor((Integer) Utils.evaluate(mFraction, mTextColor, mEndTextColor));
        canvas.drawText(String.valueOf(mTexts[2]), mTextPoints[2].x, mTextPoints[2].y, mTextPaint);

    }

    private int getContentWidth() {
        //取整
        return (int) Math.ceil(mTextPaint.measureText(String.valueOf(mCount)));
    }

    private int getContentHeight() {
        return (int) mTextSize;
    }
}
