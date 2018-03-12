package com.wynne.knowledge.tree.guide.systembar;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/3/12
 */

public class StatusBarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decoView = getWindow().getDecorView().getRootView();
            int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
            decoView.setSystemUiVisibility(uiOptions);


            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }

        }
        setContentView(R.layout.status_activity);


    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dimming:
                View decoView = getWindow().getDecorView().getRootView();
                // View.SYSTEM_UI_FLAG_LOW_PROFILE   状态栏与导航栏 颜色变暗
                // View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  隐藏导航栏
                // View.SYSTEM_UI_FLAG_FULLSCREEN 隐藏状态栏
                // SYSTEM_UI_FLAG_IMMERSIVE_STICKY  隐藏导航栏与状态栏 下滑之后显示一段时间自动消失,不会被消除标记
                int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
                decoView.setSystemUiVisibility(uiOptions);
                break;
            case R.id.btn_hide:
                hideSystemUI();

                break;
            default:
                break;
        }
    }

    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View mDecorView = getWindow().getDecorView().getRootView();
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);


        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


}
