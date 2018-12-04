package com.wynne.knowledge.main.widget.ruler;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.EdgeEffect;
import android.widget.OverScroller;

/**
 * 艺术探索 第四章学习
 * Created by Wynne on 2018/7/20.
 *
 * @author xxw
 */

public abstract class BaseInnerRuler extends View {
    protected Ruler mRuler;
    protected Context mContext;
    /**
     * 当前刻度值
     */
    protected float mCurrentScale = 0;
    /**
     * 大刻度里的小刻度个数
     */
    protected int mCount = 10;
    /**
     * 最大刻度值
     */
    protected int mMaxLength;
    /**
     * 大刻度一半
     */
    protected int mDrawOffest;
    protected Paint mSmallScalePaint;
    protected Paint mLargeScalePaint;
    protected Paint mTextPaint;
    protected Paint mOutLinePaint;
    protected OverScroller mOverScroller;
    /**
     * 速度获取
     */
    protected VelocityTracker mVelocityTracker;
    /**
     * 惯性最大最小速度
     */
    protected int mMaximumVelocity, mMinimumVelocity;


    /**
     * 改变回调
     */
    protected RulerCallBack mRulerCallBack;
    /**
     * 开始与结束的边缘效果
     */
    protected EdgeEffect mStartEdgeEffect, mEndEdgeEffect;
    /**
     * 边缘效果长度,最大可滑动距离,最小滑动距离
     */
    protected int mEdgeLength, mMaxPosition, mMinPosition;

    protected int mLength;

    public BaseInnerRuler(Context context, Ruler ruler) {
        super(context);
        mRuler = ruler;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mMaxLength = mRuler.getScaleMax() - mRuler.getScaleMin();
        mCurrentScale = mRuler.getCurrentScale();
        mCount = mRuler.getCount();

        mDrawOffest = mCount * mRuler.getInterval() / 2;

        initPaints();

        mOverScroller = new OverScroller(mContext);

        mVelocityTracker = VelocityTracker.obtain();
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

        initEdgeEffects();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                goToScale(mCurrentScale);
            }
        });

        checkAPILevel();
    }

    private void checkAPILevel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(LAYER_TYPE_NONE, null);
        }
    }

    public void initEdgeEffects() {
        //是否开启边缘
        if (mRuler.isCanEdgeEffect()) {
            if (mStartEdgeEffect == null || mEndEdgeEffect == null) {
                mStartEdgeEffect = new EdgeEffect(mContext);
                mEndEdgeEffect = new EdgeEffect(mContext);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    mStartEdgeEffect.setColor(mRuler.getEdgeColor());
                    mEndEdgeEffect.setColor(mRuler.getEdgeColor());
                }
                mEdgeLength = mRuler.getCursorHeight() + mRuler.getInterval() * mRuler.getCount();
            }
        }
    }

    private void initPaints() {
        mSmallScalePaint = new Paint();
        mSmallScalePaint.setStrokeWidth(mRuler.getScaleSmallWidth());
        mSmallScalePaint.setColor(mRuler.getScaleColor());
        mSmallScalePaint.setStrokeCap(Paint.Cap.ROUND);


        mLargeScalePaint = new Paint();
        mLargeScalePaint.setColor(mRuler.getScaleColor());
        mLargeScalePaint.setStrokeWidth(mRuler.getScaleLargeWidth());
        mLargeScalePaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mRuler.getTextColor());
        mTextPaint.setTextSize(mRuler.getTextSize());
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mOutLinePaint = new Paint();
        mOutLinePaint.setStrokeWidth(0);
        mOutLinePaint.setColor(mRuler.getScaleColor());
    }

    /**
     * 指向某一个刻度
     *
     * @param scale
     */
    protected abstract void goToScale(float scale);

    /**
     * 滚动到当前刻度值
     */
    protected abstract void scrollBackToCurrentScale();

    /**
     * 刷新大小
     */
    public abstract void refreshSize();


    public void setRulerCallBack(RulerCallBack mRulerCallBack) {
        this.mRulerCallBack = mRulerCallBack;
    }


    @Override
    public void computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            scrollTo(mOverScroller.getCurrX(), mOverScroller.getCurrY());

            if (!mOverScroller.computeScrollOffset() && mCurrentScale != Math.round(mCurrentScale)) {
                scrollBackToCurrentScale();
            }
            invalidate();
        }
    }
}
