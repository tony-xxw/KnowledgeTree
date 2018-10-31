package com.wynne.knowledge.tree.bookmark.activity;

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
        switch (v.getId()) {
            case R.id.btn_cancel:
                shadowView.setShadow(true);
                break;
            case R.id.btn1:
                blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.INNER);
                break;
            case R.id.btn2:
                blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.SOLID);
                break;
            case R.id.btn3:
                blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.NORMAL);
                break;
            case R.id.btn4:
                blurMaskFilterView.setMaskFilter(BlurMaskFilter.Blur.OUTER);
                break;
            default:
                break;
        }
    }
}
