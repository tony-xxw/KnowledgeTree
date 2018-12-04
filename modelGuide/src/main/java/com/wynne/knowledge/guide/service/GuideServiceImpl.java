package com.wynne.knowledge.guide.service;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wynne.knowledge.guide.home.GuideFragment;
import com.wynne.knowledge.base.service.base.IBaseService;

/**
 * @author Wynne
 */
public class GuideServiceImpl implements IBaseService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        FragmentTransaction transaction = manager.beginTransaction();
        GuideFragment mainFragment = new GuideFragment();
        transaction.add(containerId, mainFragment, tag).addToBackStack(tag).commit();
        return mainFragment;
    }
}
