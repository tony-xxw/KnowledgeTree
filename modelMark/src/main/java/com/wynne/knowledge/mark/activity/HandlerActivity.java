package com.wynne.knowledge.mark.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;


import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.widget.Sample;

import java.lang.ref.SoftReference;

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

    protected int size = 1;

    MyHandler myHandler;

    public void onHandler(View v) {
        int i = v.getId();
        if (i == R.id.btn_handler) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = myHandler.obtainMessage();
                    message.what = 1;
                    myHandler.sendMessageDelayed(message, 1000);
                    myHandler.sendEmptyMessage(1);
                    myHandler.sendEmptyMessage(1);

                }
            }).start();
        } else {
        }
    }

    @Override
    public void initView() {
        //不能直接传activity给静态变量，使用getApplication() 与静态变量生命周期同步
        sample = new Sample(this);
        myHandler = new MyHandler(new SoftReference<HandlerActivity>(this));

        int i = 2;

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
        private SoftReference<HandlerActivity> mSoft;

        public MyHandler(SoftReference<HandlerActivity> softReference) {
            mSoft = softReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("XXW", "size" + mSoft.get().size);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }
}
