package com.wynne.knowledge.mark.art.charpter8;

import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.http.PATCH;

/**
 * @author Wynne
 */
public class WindowActivity extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.art_window_activity;
    }


    @OnClick({R.id.btn_add_window})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_window:
                addWindow();
                break;
            default:
                break;
        }
    }

    private void addWindow() {
        Button button = new Button(this);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0, PixelFormat.TRANSPARENT);
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        params.gravity = Gravity.START | Gravity.TOP;
        params.x = 100;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        params.y = 300;
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.addView(button, params);
        }

    }

    @Override
    public void onContentChanged() {
        Log.d("XXW", "onContentChanged");
        super.onContentChanged();
    }
}
