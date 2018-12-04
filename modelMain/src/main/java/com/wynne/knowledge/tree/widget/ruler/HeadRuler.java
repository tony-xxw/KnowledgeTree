package com.wynne.knowledge.tree.widget.ruler;

import android.content.Context;
import android.graphics.Canvas;

import com.wynne.knowledge.tree.utils.StringUtils;


/**
 * @author Wynne
 * @date 2018/7/20
 */

public class HeadRuler extends BaseDirectionRuler {
    private Ruler mRuler;


    public HeadRuler(Context context, Ruler ruler) {
        super(context, ruler);
        mRuler = ruler;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScale(canvas);
        drawEdgeEffect(canvas);
    }


    private void drawScale(Canvas canvas) {
        int start = getScrollX() / mRuler.getInterval() + mRuler.getScaleMin();
        int end = ((getScrollX() + canvas.getWidth())) / mRuler.getInterval() + mRuler.getScaleMin();

        for (int i = start; i < end; i++) {
            float locationX = (i - mRuler.getScaleMin()) * mRuler.getInterval();

            if (i >= mRuler.getScaleMin() && i <= mRuler.getScaleMax()) {
                if (i % mCount == 0) {
                    canvas.drawLine(locationX, 0, locationX, mRuler.getScaleLargeLength(), mLargeScalePaint);
                    canvas.drawText(StringUtils.resultValueOf(i, mRuler.getFraction()), locationX, mRuler.getScaleMarginTop(), mTextPaint);
                } else {
                    canvas.drawLine(locationX, 0, locationX, mRuler.getScaleSmallLength(), mSmallScalePaint);
                }
            }
        }
    }

    private void drawEdgeEffect(Canvas canvas) {
        if (mRuler.isCanEdgeEffect()) {
            if (!mStartEdgeEffect.isFinished()) {
                int count = canvas.save();
                canvas.rotate(270);
                canvas.translate(-mRuler.getCursorHeight(), 0);
                if (mStartEdgeEffect.draw(canvas)) {
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(count);
            } else {
                mStartEdgeEffect.finish();
            }

            if (!mEndEdgeEffect.isFinished()) {
                int count = canvas.save();
                canvas.rotate(90);
                canvas.translate(0, -mLength);
                if (mEndEdgeEffect.draw(canvas)) {
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(count);
            } else {
                mEndEdgeEffect.finish();
            }
        }
    }
}
