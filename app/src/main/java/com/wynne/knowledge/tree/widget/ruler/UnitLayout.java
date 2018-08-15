package com.wynne.knowledge.tree.widget.ruler;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.Utils;

/**
 * 尺子选中显示
 *
 * @author Wynne
 * @date 2018/7/23
 */

public class UnitLayout extends FrameLayout implements RulerCallBack {
    private @ColorInt
    int mScaleColor = getResources().getColor(R.color.colorForgiven);
    private int mScaleSize = 60;
    private @ColorInt
    int mUnitColor = getResources().getColor(R.color.colorForgiven);
    private int mUnitSize = 40;
    private Ruler mRuler;

    private TextView mScaleView;
    private TextView mUnitView;

    private String mUnit = "kg";

    public UnitLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }


    public UnitLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttribute(context, attrs);
    }


    public UnitLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttribute(context, attrs);
    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.unit_layout, this);
        mScaleView = (TextView) findViewById(R.id.tv_scale);
        mScaleView.setTextSize(mScaleSize);
        mScaleView.setTextColor(mScaleColor);

        mUnitView = (TextView) findViewById(R.id.tv_unit);
        mUnitView.setTextColor(mUnitColor);
        mUnitView.setTextSize(mUnitSize);

        mUnitView.setText(mUnit);

    }

    public void bindRuler(Ruler ruler) {
        mRuler = ruler;
        mRuler.setCallBack(this);
    }

    private void initAttribute(Context context, AttributeSet attributeSet) {
        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.Unit);
        mScaleColor = attr.getColor(R.styleable.Unit_scale_colors, mScaleColor);
        mScaleSize = attr.getDimensionPixelSize(R.styleable.Unit_scale_size, mScaleSize);

        mUnitColor = attr.getColor(R.styleable.Unit_unit_color, mUnitColor);
        mUnitSize = attr.getDimensionPixelSize(R.styleable.Unit_unit_size, mUnitSize);
        String text = attr.getString(R.styleable.Unit_unit);

        if (text != null) {
            mUnit = text;
        }
        attr.recycle();
    }

    @Override
    public void onScaleChanging(float scale) {

        mScaleView.setText(Utils.resultValueOf(scale, mRuler.getFraction()));
    }
}
