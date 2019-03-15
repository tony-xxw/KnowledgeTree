package com.wynne.knowledge.mark.art.charpter8;

import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import butterknife.OnClick;

/**
 * @author Wynne
 */
public class WindowActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.art_window_activity;
    }


    @OnClick({R.id.btn_add_window})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_window:
                break;
            default:
                break;
        }
    }
}
