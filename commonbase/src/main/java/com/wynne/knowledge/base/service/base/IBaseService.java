package com.wynne.knowledge.base.service.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * @author xxw
 */
public interface IBaseService {

    /**
     * 是否登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 登录用户id
     *
     * @return
     */
    String getAccountId();

    /**
     * @param activity
     * @param containerId
     * @param manager
     * @param tag
     * @return 创建 UserFragment
     */
    Fragment newCustomFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task);

    /**
     * @param activity
     * @param containerId
     * @param manager
     * @param tag
     * @param task
     * @return
     */
    Fragment newGuideFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task);

    /**
     * @param activity
     * @param containerId
     * @param manager
     * @param tag
     * @param task
     * @return
     */
    Fragment newMarkFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task);
}
