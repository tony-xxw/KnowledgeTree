package com.wynne.knowledge.base.base;


import android.os.Bundle;

import com.wynne.knowledge.base.R;
import com.wynne.knowledge.base.utils.StatusBarUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author xxw
 */
public abstract class BaseActivity extends AppCompatActivity {
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();

        if (immerseModel()) {
            StatusBarUtils.Companion.setStatusColor(ContextCompat.getColor(this, R.color.colorPrimary));
            StatusBarUtils.Companion.adaptiveStatusBar(getWindow(), false);
        }
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


    /**
     * 是否开启沉浸模式
     *
     * @return
     */
    public boolean immerseModel() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}