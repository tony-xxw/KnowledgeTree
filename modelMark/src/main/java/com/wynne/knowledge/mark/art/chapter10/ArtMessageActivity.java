package com.wynne.knowledge.mark.art.chapter10;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import java.lang.ref.SoftReference;

import butterknife.OnClick;

/**
 * @author Wynne
 */
public class ArtMessageActivity extends BaseActivity {
    private ThreadLocal<Boolean> booleanThreadLocal = new ThreadLocal<>();

    private ArtHandler mHandler = new ArtHandler(this);

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.art_message_activity;
    }


    @OnClick({R.id.tv_thread_local, R.id.tv_handler})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_thread_local) {
            runThreadLocalSample();
        }
        if (view.getId() == R.id.tv_handler) {
            runHandlerSample();
        }
    }

    private void runHandlerSample() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    Message message = mHandler.obtainMessage();
                    message.what = 100;
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private static class ArtHandler extends Handler {
        SoftReference<Activity> softReference;

        public ArtHandler(Activity activity) {
            softReference = new SoftReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (softReference.get() instanceof ArtMessageActivity) {
                ArtMessageActivity artMessageActivity = (ArtMessageActivity) softReference.get();


                Toast.makeText(artMessageActivity, msg.what + "", Toast.LENGTH_SHORT).show();
            }

        }
    }


    private void runThreadLocalSample() {
        booleanThreadLocal.set(true);
        Log.d("XXW", Thread.currentThread().getName() + ":  value     " + booleanThreadLocal.get());

        new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                booleanThreadLocal.set(false);
                Log.d("XXW", getName() + ":  value     " + booleanThreadLocal.get());
            }
        }.start();
        new Thread("Thread2") {
            @Override
            public void run() {
                super.run();
                Log.d("XXW", getName() + ":  value     " + booleanThreadLocal.get());
            }
        }.start();
    }
}
