package com.wynne.knowledge.base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author xxw
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    /**
     * 初始化
     *
     * @return
     */
    public abstract void initView();

    /**
     * 返回布局id
     *
     * @return
     */
    public abstract int getLayoutId();
}