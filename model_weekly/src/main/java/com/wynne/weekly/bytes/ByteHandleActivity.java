package com.wynne.weekly.bytes;


import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.weekly.R;

public class ByteHandleActivity extends BaseActivity {


    public void initView() {
        helloByte();
    }

    public int getLayoutId() {
        return R.layout.activity_weekly_byte_layout;
    }

    public void onClick(View v) {
    }

    public void helloByte() {
        int a = 1;
        int b = 2;
        int c = 3;
        reverse(a, b, c);
    }

    private void reverse(int a, int b, int c) {
        int temp = 0;
        temp = a;
        a = c;
        c = temp;

        for (int i = 0; i < a; i++) {
            Log.d("xxw", "i : " + i);
        }
    }

}
