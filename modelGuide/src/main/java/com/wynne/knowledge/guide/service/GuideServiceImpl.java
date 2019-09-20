package com.wynne.knowledge.guide.service;

import android.app.Activity;

import com.wynne.knowledge.guide.home.GuideFragment;
import com.wynne.knowledge.base.service.base.IBaseService;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    public Fragment newCustomFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }

    @Override
    public GuideFragment newGuideFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        FragmentTransaction transaction = manager.beginTransaction();
        GuideFragment mainFragment = new GuideFragment();
        transaction.add(containerId, mainFragment, tag).addToBackStack(tag).commit();
        return mainFragment;
    }

    @Override
    public Fragment newMarkFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }
}
