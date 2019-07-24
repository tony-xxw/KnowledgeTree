package com.wynne.knowledge.guide.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.guide.R;

/**
 * 单独开发GuideActivity
 *
 *
 *
 * @author xxw
 */
@Route(path = ARouterPath.FRAGMENT_GUIDE)
public class GuideActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId()     {
        return R.layout.guide_activity;
    }
}
