package com.wynne.knowledge.mark.interview.optimize;

import android.view.View;
import android.view.ViewStub;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xxw
 */
public class OptimizeActivity extends BaseActivity {
    @BindView(R.id.vs_sample)
    ViewStub vsSample;

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.optimize_activity;
    }

    @OnClick({R.id.btn_viewStub})
    public void onViewClicked(View view) {
        vsSample.inflate();
    }
}