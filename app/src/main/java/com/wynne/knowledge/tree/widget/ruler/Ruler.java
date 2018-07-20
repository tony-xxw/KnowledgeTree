package com.wynne.knowledge.tree.widget.ruler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/7/17
 */

public class Ruler extends ViewGroup {
    /**
     * 游标宽度
     */
    private int mCursorWidth = 8;
    /**
     * 游标长度
     */
    private int mCursorHeight = 70;
    /**
     * 游标图片
     */
    private Drawable mCursorDrawable;


    /**
     * 当前游标
     */
    private float mCurrentScale = 0;
    /**
     * 大小刻度的长度
     */

    private int mScaleSmallLength = 30;


    private int mScaleLargeLength = 60;
    /**
     * 大小刻度的宽度
     */
    private int mScaleSmallWidth = 3, mScaleLargeWidth = 5;

    /**
     * 刻度之间的距离,一个大刻度中的小刻度个数
     */
    private int mScaleInterval = 18, mCount = 10;


    /**
     * 最小刻度值(默认为0),最大刻度值(默认为100)
     */
    private int mScaleMin = 0;


    private int mScaleMax = 1000;
    /**
     * 两边边距
     */
    private int mStartWidthEndPadding = 0;

    /**
     * 是否启用边缘效应
     */
    private boolean mCanEdgeEffect = true;
    /**
     * 边缘颜色
     */
    private @ColorInt
    int mEdgeColor = getResources().getColor(R.color.colorForgiven);
    /**
     * 单位换算因子
     */
    private float mFraction = 0.1f;
    /**
     * 尺子背景
     */
    private Drawable mRulerBackGround;
    /**
     * 尺子背景颜色
     */
    private int mRulerBackGroundColor = getResources().getColor(R.color.colorDirtyWithe);
    /**
     * 数字字体大小
     */
    private int mTextSize = 28;

    //数字Text颜色
    private
    @ColorInt
    int mTextColor = getResources().getColor(R.color.colorLightBlack);
    //刻度颜色
    private
    @ColorInt
    int mScaleColor = getResources().getColor(R.color.colorGray);

    private BaseInnerRuler mHeadRuler;

    private Context mContext;

    private int mPaddingLeft = 0, mPaddingTop = 0, mPaddingRight = 0, mPaddingBottom = 0;

    public Ruler(Context context) {
        super(context);
        initRuler(context);
    }


    public Ruler(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        initRuler(context);
    }

    public Ruler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initRuler(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Ruler);
        mCursorWidth = typedArray.getDimensionPixelOffset(R.styleable.Ruler_cursor_width, mCursorWidth);
        mCursorHeight = typedArray.getDimensionPixelOffset(R.styleable.Ruler_cursor_length, mCursorHeight);
        mCursorDrawable = typedArray.getDrawable(R.styleable.Ruler_cursor_drawable);
        mCurrentScale = typedArray.getFloat(R.styleable.Ruler_current_scale, (mScaleMax + mScaleMin) / 2);
        mScaleSmallWidth = typedArray.getDimensionPixelOffset(R.styleable.Ruler_scale_width_large, mScaleSmallWidth);
        mScaleSmallLength = typedArray.getDimensionPixelOffset(R.styleable.Ruler_scale_length_small, mScaleSmallLength);
        mScaleLargeWidth = typedArray.getDimensionPixelOffset(R.styleable.Ruler_scale_width_large, mScaleLargeWidth);
        mScaleLargeLength = typedArray.getDimensionPixelOffset(R.styleable.Ruler_scale_length_large, mScaleLargeLength);
        mScaleInterval = typedArray.getDimensionPixelOffset(R.styleable.Ruler_scale_interval, mScaleInterval);
        mScaleMin = typedArray.getInteger(R.styleable.Ruler_scale_min, mScaleMin);
        mScaleMax = typedArray.getInteger(R.styleable.Ruler_scale_max, mScaleMax);
        mStartWidthEndPadding = typedArray.getDimensionPixelOffset(R.styleable.Ruler_ruler_start_end, mStartWidthEndPadding);
        mEdgeColor = typedArray.getColor(R.styleable.Ruler_edgeColor, mEdgeColor);
        mCanEdgeEffect = typedArray.getBoolean(R.styleable.Ruler_canEdgeEffect, mCanEdgeEffect);
        mFraction = typedArray.getFloat(R.styleable.Ruler_factor, mFraction);
        mCount = typedArray.getInteger(R.styleable.Ruler_numberCount, mCount);
        mRulerBackGround = typedArray.getDrawable(R.styleable.Ruler_ruler_background);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.Ruler_number_text_size, mTextSize);
        mTextColor = typedArray.getColor(R.styleable.Ruler_number_text_color, mTextColor);
        mScaleColor = typedArray.getColor(R.styleable.Ruler_scale_color, mScaleColor);
        if (mCursorDrawable == null) {
            mCursorDrawable = getResources().getDrawable(R.drawable.bg_advertisement);
        }
        if (mRulerBackGround == null) {
            mRulerBackGroundColor = typedArray.getColor(R.styleable.Ruler_ruler_background, mRulerBackGroundColor);
        }
        typedArray.recycle();
    }

    private void initRuler(Context context) {
        mContext = context;
        mHeadRuler = new HeadRuler(mContext, this);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mHeadRuler.setLayoutParams(params);

        addView(mHeadRuler);

        setWillNotDraw(false);


        //初始化游标显示位置
        initDrawable();
        initRulerBackground();
    }

    private void initRulerBackground() {
        if (mRulerBackGround != null) {
            mHeadRuler.setBackground(mRulerBackGround);
        } else {
            mHeadRuler.setBackgroundColor(mRulerBackGroundColor);
        }
    }

    private void initDrawable() {
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                mCursorDrawable.setBounds(
                        (getWidth() - mCursorWidth) / 2, 0,
                        (getWidth() + mCursorWidth) / 2, mCursorHeight);
                return false;
            }
        });
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mCursorDrawable.draw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            mHeadRuler.layout(mPaddingLeft, mPaddingTop, r - l - mPaddingRight, b - t - mPaddingBottom);
        }
    }

    public int getScaleMin() {
        return mScaleMin;
    }

    public int getScaleMax() {
        return mScaleMax;
    }

    public float getCurrentScale() {
        return mCurrentScale;
    }

    public int getCount() {
        return mCount;
    }

    public int getInterval() {
        return mScaleInterval;
    }

    public int getCursorWidth() {
        return mCursorWidth;
    }

    public int getCursorHeight() {
        return mCursorHeight;
    }

    public Drawable getCursorDrawable() {
        return mCursorDrawable;
    }


    public int getScaleSmallLength() {
        return mScaleSmallLength;
    }

    public int getScaleLargeLength() {
        return mScaleLargeLength;
    }

    public int getScaleSmallWidth() {
        return mScaleSmallWidth;
    }

    public int getScaleLargeWidth() {
        return mScaleLargeWidth;
    }

    public int getScaleInterval() {
        return mScaleInterval;
    }


    public int getStartWidthEndPadding() {
        return mStartWidthEndPadding;
    }

    public boolean isCanEdgeEffect() {
        return mCanEdgeEffect;
    }

    public int getEdgeColor() {
        return mEdgeColor;
    }

    public float getFraction() {
        return mFraction;
    }

    public Drawable getRulerBackGround() {
        return mRulerBackGround;
    }

    public int getRulerBackGroundColor() {
        return mRulerBackGroundColor;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getScaleColor() {
        return mScaleColor;
    }
}
