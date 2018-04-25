package com.wynne.knowledge.tree.guide.systemstatus;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/3/12
 */

public class StatusBarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_activity);

        //全屏模式  隐藏了状态栏
//        hideActionWidthStatus();
        //全屏模式  状态栏覆盖在布局上
        saveStatusTextWithImage();

        hideActionBar();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dimming:

                break;
            case R.id.btn_hide:
                break;
            default:
                break;
        }
    }


    public void hideActionWidthStatus() {
        View dectView = getWindow().getDecorView();
        //全屏模式FLAG
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        dectView.setSystemUiVisibility(option);
    }

    public void saveStatusTextWithImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }


    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}
