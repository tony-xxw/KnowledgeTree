package com.wynne.knowledge.tree.widget.ruler;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.OverScroller;

/**
 * Created by Wynne on 2018/7/20.
 *
 * @author xxw
 */

public abstract class BaseInnerRuler extends View {
    private Ruler mRuler;
    private Context mContext;
    /**
     * 当前刻度值
     */
    private float mCurrentScale = 0;
    /**
     * 大刻度里的小刻度个数
     */
    private int mCount = 10;
    /**
     * 最大刻度值
     */
    private int mMaxLength;
    /**
     * 大刻度一半
     */
    private int mDrwaOffest;
    private Paint mSmallScalePaint;
    private Paint mLargeScalePaint;
    private Paint mTextPaint;
    private Paint mOutLinePaint;
    private OverScroller mOverScroller;

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

        mDrwaOffest = mCount * mRuler.getInterval() / 2;

        initPaints();

        mOverScroller = new OverScroller(mContext);

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
}
