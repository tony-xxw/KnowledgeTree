package com.wynne.knowledge.mark.art.chapter10;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.art.Sun;
import com.wynne.knowledge.mark.interview.ThreadFactoryActivity;
import com.wynne.knowledge.mark.service.MyIntentService;

import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.OnClick;

/**
 * 测试新密钥~~
 * @author Wynne
 */
public class ArtMessageActivity extends BaseActivity {
    private ThreadLocal<Boolean> booleanThreadLocal = new ThreadLocal<>();

    private ArtHandler mHandler = new ArtHandler(this);

    @Override
    public void initView() {
        Sun sun = new Sun();
    }

    @Override
    public int getLayoutId() {
        return R.layout.art_message_activity;
    }


    @OnClick({R.id.tv_thread_local, R.id.tv_handler, R.id.tv_async_task, R.id.tv_IntentService, R.id.tv_thread_pool})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_thread_local) {
            runThreadLocalSample();
        }
        if (view.getId() == R.id.tv_handler) {
            runHandlerSample();
        }
        if (view.getId() == R.id.tv_async_task) {
            try {
                new ArtAsyncTask().execute(new URL("http://www.baidu.com"),
                        new URL("http://www.renyugang.cn"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (view.getId() == R.id.tv_IntentService) {
            Intent intent = new Intent(this, MyIntentService.class);
            intent.putExtra("task_action", "action1");
            startService(intent);
            intent.putExtra("task_action", "action2");
            startService(intent);
            intent.putExtra("task_action", "action3");
            startService(intent);
        }

        if (view.getId() == R.id.tv_thread_pool) {

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

    private class ArtAsyncTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                publishProgress(i);
                if (isCancelled()) {
                    break;
                }
            }
            return totalSize;
        }

        @Override
        protected void onPreExecute() {
            Log.d("XXW", "onPreExecute");
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Long aLong) {
            Log.d("XXW", "onPostExecute   :" + aLong);
            super.onPostExecute(aLong);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("XXW", "onProgressUpdate   :" + values.length);
            super.onProgressUpdate(values);
        }
    }
}
