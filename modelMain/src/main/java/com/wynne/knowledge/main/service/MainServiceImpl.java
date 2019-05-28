package com.wynne.knowledge.main.service;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wynne.knowledge.base.service.base.IBaseService;
import com.wynne.knowledge.main.main.CustomFragment;

/**
 * @author xxw
 */
public class MainServiceImpl implements IBaseService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public Fragment newCustomFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        FragmentTransaction transaction = manager.beginTransaction();
        CustomFragment mainFragment = new CustomFragment();
        transaction.add(containerId, mainFragment, tag).addToBackStack(tag).commit();
        return mainFragment;
    }

    @Override
    public Fragment newGuideFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {

        return null;
    }

    @Override
    public Fragment newMarkFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }
}
