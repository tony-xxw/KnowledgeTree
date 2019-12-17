package com.wynne.weekly;


import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.weekly.utils.DetectionMethod;
import com.wynne.weekly.utils.DetectionUtil;
import com.wynne.weekly.week.TransformActivity;

import static com.wynne.knowledge.base.constant.ARouterPath.BASE_WEEKLY;


@Route(path = BASE_WEEKLY)
public class WeeklyActivity extends BaseActivity {

    public void initView() {
        startActivity(new Intent(this, TransformActivity.class));
    }


    public int getLayoutId() {
        return R.layout.activity_weekly_main_layout;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTransform:
                startActivity(new Intent(this, TransformActivity.class));
                break;
            default:
                break;
        }
    }
}