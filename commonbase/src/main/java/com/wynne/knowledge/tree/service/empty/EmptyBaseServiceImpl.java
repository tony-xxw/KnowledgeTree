package com.wynne.knowledge.tree.service.empty;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wynne.knowledge.tree.service.base.IBaseService;

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
    public Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, String tag, String task) {
        return null;
    }


}
