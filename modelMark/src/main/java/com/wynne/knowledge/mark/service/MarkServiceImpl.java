package com.wynne.knowledge.mark.service;

import android.app.Activity;



import com.wynne.knowledge.mark.home.BookMarkFragment;
import com.wynne.knowledge.base.service.base.IBaseService;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author xxw
 */
public class MarkServiceImpl implements IBaseService {
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
    public BookMarkFragment newGuideFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }

    @Override
    public Fragment newMarkFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        FragmentTransaction transaction = manager.beginTransaction();
        BookMarkFragment mainFragment = new BookMarkFragment();
        transaction.add(containerId, mainFragment, tag).addToBackStack(tag).commit();
        return mainFragment;
    }
}
