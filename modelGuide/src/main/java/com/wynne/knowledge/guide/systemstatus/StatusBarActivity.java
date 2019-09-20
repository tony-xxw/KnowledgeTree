package com.wynne.knowledge.guide.systemstatus;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.guide.R;

import androidx.appcompat.app.ActionBar;
import androidx.core.view.ViewCompat;

/**
 * @author Wynne
 * @date 2018/3/12
 */

public class StatusBarActivity extends BaseActivity {


    @Override
    public void initView() {
        saveStatusTextWithImage();

        hideActionBar();
    }

    @Override
    public int getLayoutId() {
        return R.layout.status_activity;
    }


    //
    private void saveStatusTextWithImage() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            //窗口属性
            WindowManager.LayoutParams winLayoutParams = window.getAttributes();
            int flagStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winLayoutParams.flags |= flagStatus;
            window.setAttributes(winLayoutParams);
        } else {
            Window window = getWindow();

            //删除半透明的状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //指示此窗口负责绘制系统栏背景的标志
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏可见
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);


            //设置状态栏颜色  还是占位状态栏的空间
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
                ViewCompat.requestApplyInsets(mChildView);
            }

        }
    }


    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_dimming) {
        } else if (i == R.id.btn_hide) {
        } else {
        }
    }


    public void hideActionWidthStatus() {
        View dectView = getWindow().getDecorView();
        //全屏模式FLAG
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        dectView.setSystemUiVisibility(option);
    }


    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


}
