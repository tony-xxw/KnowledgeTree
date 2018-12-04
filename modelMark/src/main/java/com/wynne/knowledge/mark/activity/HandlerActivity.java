package com.wynne.knowledge.mark.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;


import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

/**
 * @author Wynne
 * @date 2018/7/30
 */

public class HandlerActivity extends BaseActivity {

    /**
     * 静态是全局变量,跟随整个App的生命周期,设为静态会内存泄漏
     ***/
    public Context context;
    /**
     * 静态外部内持有activity 引用,当activity退转之后,context持有activity引用 导致内存泄漏
     */
    public static Sample sample;

    MyHandler myHandler;

    public void onHandler(View v) {
        int i = v.getId();
        if (i == R.id.btn_handler) {
        } else {
        }
    }

    @Override
    public void initView() {
        //不能直接传activity给静态变量，使用getApplication() 与静态变量生命周期同步
        sample = new Sample(this);
        myHandler = new MyHandler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.handler_layout;
    }


    class Sample {
        Sample(Context context) {

        }
    }

    private static class MyHandler extends Handler {


    }
}
