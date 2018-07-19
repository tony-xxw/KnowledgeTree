package com.wynne.knowledge.tree.custom.window;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/7/18
 */

public class WindowActivity extends AppCompatActivity {
    Button mBtnWindow;
    Drawable drawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_activity);
        mBtnWindow = (Button) findViewById(R.id.btn_wd);


        drawable = getResources().getDrawable(R.drawable.star_light);
        drawable.setBounds(100, 10, 100, 50);

        mBtnWindow.setBackground(drawable);
    }
}
