package com.wynne.knowledge.base.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.base.view.BaseFragment;

/**
 * A/F 返回类
 *
 * @author xxw
 */
public class ARouterUtils {
    /**
     * 根据path返回Fragment
     *
     * @param path path
     * @return fragment
     */
    public static BaseFragment getFragment(String path) {
        return (BaseFragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    /**
     * 根据path返回Activity
     *
     * @param path path
     * @return Activity
     */
    public static BaseActivity getActivity(String path) {
        return (BaseActivity) ARouter.getInstance()
                .build(path)
                .navigation();
    }
}
