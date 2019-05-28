package com.wynne.knowledge.main.widget.thumb;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.utils.StringUtils;
import com.wynne.knowledge.home.widget.thumb.ThumbView;

/**
 * Created by Wynne on 2018/7/3.
 */

public class ThumbUpView extends LinearLayout implements View.OnClickListener {
    private static final float DEFAULT_DRAWABLE_PADDING = 4f;
    private boolean mIsThumbUp;
    private int mCount;
    private float mDrawablePadding;
    private int mTextColor;
    private float mTextSize;
    private boolean mNeedChangeChildView;
    private CountView mCountView;
    private ThumbView mThumbView;
    private int mTopMargin;

    public ThumbUpView(Context context) {
        super(context);
    }

    public ThumbUpView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThumbUpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedAttrs = context.obtainStyledAttributes(attrs, R.styleable.ThumbUpView);
        mDrawablePadding = typedAttrs.getDimension(R.styleable.ThumbUpView_tuv_drawable_padding, StringUtils.dip2px(context, DEFAULT_DRAWABLE_PADDING));
        mCount = typedAttrs.getInteger(R.styleable.ThumbUpView_tuv_count, 0);
        mTextColor = typedAttrs.getColor(R.styleable.ThumbUpView_tuv_text_color, Color.parseColor(CountView.DEFAULT_TEXT_COLOR));
        mTextSize = typedAttrs.getDimension(R.styleable.ThumbUpView_tuv_text_size, StringUtils.dip2px(context, CountView.DEFAULT_TEXT_SIZE));
        mIsThumbUp = typedAttrs.getBoolean(R.styleable.ThumbUpView_tuv_isThumbUp, false);
        typedAttrs.recycle();
        init();
    }

    private void init() {
        removeAllViews();
        setClipChildren(false);
        setOrientation(HORIZONTAL);

        addThumbView();
        addCountView();

        setPadding(0, 0, 0, 0, false);
        setOnClickListener(this);
    }

    private void setPadding(int left, int top, int right, int bottom, boolean needChange) {
        mNeedChangeChildView = needChange;
        setPadding(left, top, right, bottom);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if (mNeedChangeChildView) {
            resetThumbParams();
            resetCountViewParams();
            mNeedChangeChildView = false;
        } else {
            super.setPadding(left, top, right, bottom);
        }
    }

    private void resetCountViewParams() {
        LayoutParams params = (LayoutParams) mCountView.getLayoutParams();
        if (mTopMargin > 0) {
            params.topMargin = mTopMargin;
        }
        params.leftMargin = (int) mDrawablePadding;
        params.topMargin += getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        params.rightMargin = getPaddingRight();
        mCountView.setLayoutParams(params);
    }

    private void resetThumbParams() {
        LayoutParams params = (LayoutParams) mThumbView.getLayoutParams();
        if (mTopMargin > 0) {
            params.topMargin = mTopMargin;
        }
        params.leftMargin = (int) mDrawablePadding;
        params.topMargin += getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        params.rightMargin = getPaddingRight();
        mThumbView.setLayoutParams(params);
    }

    private void addCountView() {
        mCountView = new CountView(getContext());
        mCountView.setTextColor(mTextColor);
        mCountView.setTextSize(mTextSize);
        mCountView.setCount(mCount);

        addView(mCountView, getCountParams());
    }

    private void addThumbView() {
        mThumbView = new ThumbView(getContext());
        mThumbView.setIsThumbUp(mIsThumbUp);
        TuvPoint circlePoint = mThumbView.getCirclePoint();
        mTopMargin = (int) (circlePoint.y - mTextSize / 2);

        addView(mThumbView, getThumbParams());
    }


    public ThumbUpView setCount(int mCount) {
        this.mCount = mCount;
        mCountView.setCount(mCount);
        return this;
    }

    public ThumbUpView setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mCountView.setTextColor(mCount);
        return this;
    }

    public ThumbUpView setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        mCountView.setTextSize(mCount);
        return this;
    }

    public ThumbUpView setThumbUp(boolean isThumbUp) {
        this.mIsThumbUp = isThumbUp;
        mThumbView.setIsThumbUp(mIsThumbUp);
        return this;
    }

    @Override
    public void onClick(View v) {
        mIsThumbUp = !mIsThumbUp;
        if (mIsThumbUp) {
            mCountView.calculateChangeNum(1);
        } else {
            mCountView.calculateChangeNum(-1);
        }
        mThumbView.startAnim();
    }

    public LayoutParams getCountParams() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mTopMargin > 0) {
            params.topMargin = mTopMargin;
        }
        params.leftMargin = (int) mDrawablePadding;
        params.topMargin += getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        params.rightMargin = getPaddingRight();
        return params;
    }

    public LayoutParams getThumbParams() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mTopMargin < 0) {
            params.topMargin = mTopMargin;//设置这个距离是为了文字与拇指居中显示
        }
        params.leftMargin = getPaddingLeft();
        params.topMargin += getPaddingTop();
        params.bottomMargin = getPaddingBottom();
        return params;
    }


    public void setThumbUpClickListener(ThumbView.ThumbUpClickListener listener) {
        mThumbView.setThumbUpClickListener(listener);
    }

}
