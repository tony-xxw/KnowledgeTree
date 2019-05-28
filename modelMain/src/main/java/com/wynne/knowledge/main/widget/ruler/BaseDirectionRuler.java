package com.wynne.knowledge.main.widget.ruler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;


/**
 * @author Wynne
 * @date 2018/7/20  
 */

public abstract class BaseDirectionRuler extends BaseInnerRuler {
    /**
     * 尺子从屏幕一般开始画
     */
    protected int mHalfWidth = 0;

    public BaseDirectionRuler(Context context, Ruler ruler) {
        super(context, ruler);
    }

    private float mLastX = 0;


    /**
     * 刻度值转刻度
     */
    private float scrollXtoScale(int scale) {
        return ((float) (scale - mMinPosition) / mLength) * mMaxLength + mRuler.getScaleMin();
    }

    /**
     * 刻度转刻度值
     *
     * @param scale
     * @return
     */
    private int scaleToScrollX(float scale) {
        //当前位置 - 最小的刻度值 / 总刻度 * 总刻度值 得到目前所占用的刻度值 再加上默认滑动位置
        return (int) ((scale - mRuler.getScaleMin()) / mMaxLength * mLength + mMinPosition);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mOverScroller.isFinished()) {
                    mOverScroller.abortAnimation();
                }
                mLastX = currentX;
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = mLastX - currentX;
                mLastX = currentX;
                scrollBy((int) moveX, 0);
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int velocity = (int) mVelocityTracker.getXVelocity();
                if (Math.abs(velocity) > mMinimumVelocity) {
                    fling(-velocity);
                } else {
                    scrollBackToCurrentScale();
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                releaseEdgeEffect();
                break;
            case MotionEvent.ACTION_CANCEL:
                if (!mOverScroller.isFinished()) {
                    mOverScroller.abortAnimation();
                }

                scrollBackToCurrentScale();
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                releaseEdgeEffect();
                break;
            default:
                break;
        }
        return true;
    }

    private void releaseEdgeEffect() {
        if (mRuler.isCanEdgeEffect()) {
            mStartEdgeEffect.onRelease();
            mEndEdgeEffect.onRelease();
        }
    }

    private void fling(int i) {
        mOverScroller.fling(getScrollX(), 0, i, 0, mMinPosition - mEdgeLength, mMaxPosition + mEdgeLength, 0, 0);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (x < mMinPosition) {
            getStartEdgeEffect(x);
            x = mMinPosition;
        }

        if (x > mMaxPosition) {
            goEndEdgeEffect(x);
            x = mMaxPosition;
        }

        if (x != getScrollX()) {
            super.scrollTo(x, y);
        }

        mCurrentScale = scrollXtoScale(x);
        if (mRulerCallBack != null) {
            mRulerCallBack.onScaleChanging(Math.round(mCurrentScale));
        }
    }


    private void goEndEdgeEffect(int x) {
        if (mRuler.isCanEdgeEffect()) {
            if (!mOverScroller.isFinished()) {
                mEndEdgeEffect.onAbsorb((int) mOverScroller.getCurrVelocity());
                mOverScroller.abortAnimation();
            } else {
                mEndEdgeEffect.onPull((float) (x - mMaxPosition) / (mEdgeLength) * 3 + 0.3f);
                mEndEdgeEffect.setSize(mRuler.getCursorHeight(), getWidth());
            }
            postInvalidateOnAnimation();
        }
    }

    /**
     * 最开始的滑动
     *
     * @param x
     */
    private void getStartEdgeEffect(int x) {
        if (mRuler.isCanEdgeEffect()) {
            if (!mOverScroller.isFinished()) {
                mStartEdgeEffect.onAbsorb((int) mOverScroller.getCurrVelocity());
                mOverScroller.abortAnimation();
            } else {
                mStartEdgeEffect.onPull((float) (mMinPosition - x) / (mEdgeLength) * 3 + 0.3f);
                mStartEdgeEffect.setSize(mRuler.getCursorHeight(), getWidth());
            }
            postInvalidateOnAnimation();
        }
    }

    @Override
    protected void goToScale(float scale) {
        mCurrentScale = Math.round(scale);
        scrollTo(scaleToScrollX(mCurrentScale), 0);
    }

    @Override
    public void refreshSize() {
        //获取刻度值总数
        mLength = (mRuler.getScaleMax() - mRuler.getScaleMin()) * mRuler.getInterval();
        mHalfWidth = getWidth() / 2;
        //滑动位置从屏幕的一半开始
        mMinPosition = -mHalfWidth;
        //最大的滑动位置也是到屏幕一般
        mMaxPosition = mLength - mHalfWidth;
    }

    @Override
    protected void scrollBackToCurrentScale() {
        mOverScroller.startScroll(getScrollX(), 0, scaleToScrollX(Math.round(mCurrentScale)) - getScrollX(), 0, 500);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refreshSize();
    }
}
