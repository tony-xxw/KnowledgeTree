package com.wynne.knowledge.main.window;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;

/**
 * @author Wynne
 * @date 2018/7/18
 */

public class WindowActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {
    Button mCreateWindowButton;
    Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager manager;


    @Override
    public void initView() {
        mCreateWindowButton = (Button) findViewById(R.id.btn_wd);
        mCreateWindowButton.setOnClickListener(this);

        manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.window_activity;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults != null && grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initWindow();
                } else {
                    Toast.makeText(WindowActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                manager.updateViewLayout(mFloatingButton, mLayoutParams);
                break;
            default:
                break;

        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_wd) {
            mFloatingButton = new Button(this);
            mFloatingButton.setText("click me");
            mLayoutParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0,
                    PixelFormat.TRANSPARENT);
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
            mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
            mLayoutParams.x = 100;
            mLayoutParams.y = 300;
            mFloatingButton.setOnTouchListener(this);
            manager.addView(mFloatingButton, mLayoutParams);

        } else {
        }
    }
}
