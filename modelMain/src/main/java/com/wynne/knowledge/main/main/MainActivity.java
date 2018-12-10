package com.wynne.knowledge.main.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.base.view.BaseFragment;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.base.utils.ARouterUtils;
import com.wynne.knowledge.main.R;

import java.lang.reflect.Field;

import static com.wynne.knowledge.base.constant.FlagConstant.BOOK_TAG_TASK;
import static com.wynne.knowledge.base.constant.FlagConstant.CUSTOM_TAG_TASK;
import static com.wynne.knowledge.base.constant.FlagConstant.GUIDE_TAG_TASK;

/**
 * @author Wynne
 */
public class MainActivity extends BaseActivity {
    private Fragment mCurrentFragment;

    @Override
    public void initView() {
        BottomNavigationView mBngMenu = findViewById(R.id.bnm_menu);
        mBngMenu.setOnNavigationItemSelectedListener(listener);
        disableShiftMode(mBngMenu);
        putFragment(GUIDE_TAG_TASK);
    }


    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * popBackStack()
     */
    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.custom) {
                switchFragment(CUSTOM_TAG_TASK);
                return true;
            } else if (i == R.id.guide) {
                switchFragment(GUIDE_TAG_TASK);
                return true;
            } else if (i == R.id.bookmark) {
                switchFragment(BOOK_TAG_TASK);
                return true;
            }
            return false;
        }
    };


    public void putFragment(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        BaseFragment fragment;

        if (tag.equals(CUSTOM_TAG_TASK)) {
            fragment = ARouterUtils.getFragment(ARouterPath.FRAGMENT_CUSTOM);
        } else if (tag.equals(GUIDE_TAG_TASK)) {
            fragment = ARouterUtils.getFragment(ARouterPath.FRAGMENT_GUIDE);
        } else {
            fragment = ARouterUtils.getFragment(ARouterPath.FRAGMENT_BOOKMARK);
        }
        mCurrentFragment = fragment;
        transaction.add(R.id.fl_content, fragment, tag).addToBackStack(tag).commit();

    }


    public void switchFragment(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.findFragmentByTag(tag) == null) {
            putFragment(tag);
        } else {
            transaction.hide(mCurrentFragment).show(manager.findFragmentByTag(tag)).commit();
            mCurrentFragment = manager.findFragmentByTag(tag);
        }
    }


    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        //获取子View BottomNavigationMenuView的对象
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            //设置私有成员变量mShiftingMode可以修改
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //去除shift效果
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "没有mShiftingMode这个成员变量", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "无法修改mShiftingMode的值", e);
        }
    }


}
