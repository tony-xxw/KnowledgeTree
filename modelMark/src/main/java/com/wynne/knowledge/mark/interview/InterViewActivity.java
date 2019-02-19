package com.wynne.knowledge.mark.interview;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.interview.http.HttpActivity;

/**
 * 面试集合
 *
 * @author Wynne
 */
public class InterViewActivity extends BaseActivity implements View.OnClickListener {
    Button btnHttp;

    @Override
    public void initView() {
        btnHttp = findViewById(R.id.btn_http);

        btnHttp.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.inter_view_activity;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_http) {
            startActivity(new Intent(this, HttpActivity.class));
        }
    }
}
