package com.wynne.knowledge.base.service.empty;

import android.app.Activity;


import com.wynne.knowledge.base.service.base.IBaseService;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author xxw
 */
public class EmptyBaseServiceImpl implements IBaseService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public Fragment newMarkFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }

    @Override
    public Fragment newGuideFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }

    @Override
    public Fragment newCustomFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }


}
