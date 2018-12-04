package com.wynne.knowledge.guide.material;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.wynne.knowledge.guide.R;


/**
 * Created by Wynne on 2018/4/27.
 */

public class TitleCenterToolbar extends Toolbar {
    public RelativeLayout a;
    public TextView b;
    public ImageView c;
    private boolean d = false;

    public TitleCenterToolbar(Context paramContext) {
        super(paramContext);
        a();
    }

    public TitleCenterToolbar(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a();
        a(paramContext, paramAttributeSet, 0);
    }

    public TitleCenterToolbar(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        a();
        a(paramContext, paramAttributeSet, paramInt);
    }

    private void a() {
        this.a = new RelativeLayout(getContext());
        Toolbar.LayoutParams localLayoutParams = new Toolbar.LayoutParams(-2, -1);
        localLayoutParams.gravity = 17;
        this.b = new TextView(getContext());
        this.b.setId(R.id.base_ui_title_center_tool_bar_title);
        RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams1.addRule(13);
        this.b.setSingleLine();
        this.b.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.a.addView(this.b, localLayoutParams1);
        this.c = new ImageView(getContext());
        localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
        this.c.setVisibility(GONE);
        this.c.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.c.setImageResource(R.drawable.ic_base_ui_arrow_down);
        localLayoutParams1.addRule(14);
        localLayoutParams1.addRule(3, R.id.base_ui_title_center_tool_bar_title);
        this.a.addView(this.c, localLayoutParams1);
        addView(this.a, localLayoutParams);
    }

    @SuppressLint({"PrivateResource"})
    private void a(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        TypedArray array = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Toolbar, paramInt, 0);
        paramInt = array.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        int i = array.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.b.setTextAppearance(paramContext, paramInt);
        setTitleTextAppearance(paramContext, paramInt);
        setSubtitleTextAppearance(paramContext, i);
        array.recycle();
    }

    public final void a(boolean paramBoolean) {
        this.d = true;
        String str1 = "";
        if (getTitle() != null) {
            str1 = getTitle().toString();
        }
        String str2 = str1;
        if (TextUtils.isEmpty(str1)) {
            str2 = str1;
            if (this.b.getText() != null) {
                str2 = this.b.getText().toString();
            }
        }
        setTitle(str2);
    }

    @Override
    public CharSequence getTitle() {
        if (this.d) {
            return this.b.getText();
        }
        return super.getTitle();
    }

    @Override
    public void setTitle(CharSequence paramCharSequence) {
        if (this.d) {
            super.setTitle(" ");
            this.b.setText(paramCharSequence);
            return;
        }
        super.setTitle(paramCharSequence);
        this.b.setText("");
    }

    @Override
    public void setTitleTextAppearance(Context paramContext, int paramInt) {
        super.setTitleTextAppearance(paramContext, paramInt);
        this.b.setTextAppearance(paramContext, paramInt);
    }

    @Override
    public void setTitleTextColor(int paramInt) {
        super.setTitleTextColor(paramInt);
        this.b.setTextColor(paramInt);
    }
}
