package com.wynne.knowledge.tree.activity;

import android.graphics.BlurMaskFilter;
import android.view.View;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;
import com.wynne.knowledge.tree.widget.BlurMaskFilterView;
import com.wynne.knowledge.tree.widget.ShadowView;


/**
 * @author Wynne
 * @date 2018/10/31
 */

public class ShadowActivity extends BaseActivity implements View.OnClickListener {
    ShadowView shadowView;
    BlurMaskFilterView blurMaskFilterView;

    @Override
    public void initView() {
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        shadowView = findViewById(R.id.sv_shadown);
        blurMaskFilterView = findViewById(R.id.bmfv_sample);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shadown_layout;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_cancel) {
            shadowView.setShadow(true);

        } else if (i == R.id.btn1) {
            blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.INNER);

        } else if (i == R.id.btn2) {
            blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.SOLID);

        } else if (i == R.id.btn3) {
            blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.NORMAL);

        } else if (i == R.id.btn4) {
            blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.OUTER);

        } else {
        }
    }
}
