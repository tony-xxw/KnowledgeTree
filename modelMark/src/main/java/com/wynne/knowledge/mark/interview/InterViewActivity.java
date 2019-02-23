package com.wynne.knowledge.mark.interview;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.interview.http.HttpActivity;
import com.wynne.knowledge.mark.interview.optimize.OptimizeActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 面试集合
 *
 * @author Wynne
 */
public class InterViewActivity extends BaseActivity {

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.inter_view_activity;
    }


    @OnClick({R.id.btn_optimize})
    public void onViewClicked(View v) {
        if (v.getId() == R.id.btn_optimize) {
            startActivity(new Intent(this, OptimizeActivity.class));
        } else if (v.getId() == R.id.btn_http) {
            startActivity(new Intent(this, HttpActivity.class));
        }
    }
}
