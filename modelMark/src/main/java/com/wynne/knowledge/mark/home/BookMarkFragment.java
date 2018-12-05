package com.wynne.knowledge.mark.home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.base.view.BaseFragment;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.mark.R;

/**
 * @author xxw
 */
@Route(path = ARouterPath.FRAGMENT_BOOKMARK)
public class BookMarkFragment extends BaseFragment {
    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.boorkmark_layout;
    }
}
