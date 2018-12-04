package com.wynne.knowledge.tree.service.base;

import android.app.Activity;
import android.os.Bundle;
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
    Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task);
}
