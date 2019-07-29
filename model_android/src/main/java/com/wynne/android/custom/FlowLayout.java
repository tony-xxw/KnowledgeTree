package com.wynne.android.custom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wynne.android.R;

import java.security.MessageDigest;
import java.util.List;

import retrofit2.http.POST;

public class FlowLayout extends ViewGroup {
    private int mWorkMarign;
    private int mHeightMarign;
    private Context mContext;


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext =context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.flow_view);
        mWorkMarign = typedArray.getDimensionPixelOffset(R.styleable.flow_view_wordMargin, 0);
        mHeightMarign = typedArray.getDimensionPixelOffset(R.styleable.flow_view_lineMargin, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = getPaddingLeft();
        int y = getPaddingTop();

        int contentWidth = (r - l);
        int maxItemHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            if (x + child.getMeasuredWidth() + getPaddingRight() > contentWidth) {
                x = getPaddingLeft();
                y += mHeightMarign + maxItemHeight;
                maxItemHeight = 0;
            }

            child.layout(x, y, x + child.getMeasuredWidth(), y + child.getMeasuredHeight());
            x += child.getMeasuredWidth() + mWorkMarign;
            maxItemHeight = Math.max(maxItemHeight, child.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        int maxWidth = ((MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight()));
        int lineHeight = 0;
        int lineWidth = 0;
        int lineWidthMax = 0;
        int maxitemHeight = 0;
        boolean isBegin = true;

        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);

            if (!isBegin) {
                lineWidth += mWorkMarign;
            } else {
                isBegin = false;
            }


            if (lineWidth + view.getMeasuredWidth() >= maxWidth) {
                lineHeight += maxitemHeight + mHeightMarign;
                maxitemHeight = 0;
                lineWidthMax = Math.max(lineWidthMax, lineWidth);
                lineWidth = 0;
                isBegin = true;

            }

            maxitemHeight = Math.max(view.getMeasuredHeight(), maxitemHeight);
            lineWidth += view.getMeasuredWidth();
        }

        lineHeight += maxitemHeight;
        lineWidthMax = Math.max(lineWidthMax, lineWidth);

        setMeasuredDimension(measureWidth(widthMeasureSpec, lineWidthMax), measureHeight(heightMeasureSpec, lineHeight));

    }

    private int measureWidth(int widthMeasureSpec, int lineWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            result = lineWidth + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        result = Math.max(result, getSuggestedMinimumWidth());
        return result;
    }

    private int measureHeight(int heightMeasureSpec, int lineHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            result = lineHeight + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        result = Math.max(result, getMeasuredHeight());
        return result;
    }


    public void setTags(List<String> tags) {
        //清空原有的标签
        removeAllViews();

        if (tags != null) {
            int size = tags.size();
            for (int i = 0; i < size; i++) {
                addLabel(tags.get(i));
            }
        }
    }




    private  void addLabel(String text) {
        final TextView tags = new TextView(mContext);
        tags.setPadding(10, 10, 10, 10);
        tags.setTextSize(TypedValue.COMPLEX_UNIT_PX, 72);
        tags.setTextColor( ColorStateList.valueOf(0xFF000000));
        //设置给label的背景(Drawable)是一个Drawable对象的拷贝，
        // 因为如果所有的标签都共用一个Drawable对象，会引起背景错乱。
        addView(tags);
        tags.setText(text);
    }

    public interface FlowTextProvider<T> {

        /**
         * 根据data和position返回label需要需要显示的数据。
         *
         * @param tag
         * @param position
         * @param data
         * @return
         */
        CharSequence getFlowText(TextView tag, int position, T data);
    }

}
