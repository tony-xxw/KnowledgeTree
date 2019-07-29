package com.wynne.knowledge.mark.art.chapter10;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.R2;
import com.wynne.knowledge.mark.art.Sun;
import com.wynne.knowledge.mark.service.MyIntentService;

import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;

/**
 * Mac test
 *
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


    @OnClick({R2.id.tv_thread_local, R2.id.tv_handler, R2.id.tv_thread_handler, R2.id.tv_async_task, R2.id.tv_IntentService,
            R2.id.tv_fix_thread_pool, R2.id.tv_cache_thread_pool, R2.id.tv_schedule_thread_pool, R2.id.tv_single_thread_pool})
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

        if (view.getId() == R.id.tv_fix_thread_pool) {
            runFixExecutor();
        }
        if (view.getId() == R.id.tv_cache_thread_pool) {
            runCacheExecutor();
        }
        if (view.getId() == R.id.tv_schedule_thread_pool) {
            runScheduleExecutor();
        }
        if (view.getId() == R.id.tv_single_thread_pool) {
            runSingleExecutor();
        }
        if (view.getId()==R2.id.tv_thread_handler){
            HandlerThread thread = new HandlerThread("Handler");

            thread.start();

        }
    }

    ScheduledExecutorService delaryFixedThreadPool;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(2000);
            Log.d("XXW", "runnable start");
            Log.d("XXW", "Hello executor");
            Log.d("XXW", "runnable end");
        }
    };
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            Log.d("XXW", "runnable1 start");
            SystemClock.sleep(2000);
            Log.d("XXW", "Hello executor 1");
            Log.d("XXW", "runnable1 end");
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(2000);
            Log.d("XXW", "runnable2 start");
            Log.d("XXW", "Hello executor 2");
            Log.d("XXW", "runnable2 end");
        }
    };
    Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(2000);
            Log.d("XXW", "runnable3 start");
            Log.d("XXW", "Hello executor 3");
            Log.d("XXW", "runnable3 end");
        }
    };


    private void runFixExecutor() {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
        newFixedThreadPool.execute(runnable);
        newFixedThreadPool.execute(runnable1);
        newFixedThreadPool.execute(runnable2);
        newFixedThreadPool.execute(runnable3);
    }

    private void runCacheExecutor() {
        ExecutorService cacheFixedThreadPool = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r);
            }
        });
        cacheFixedThreadPool.execute(runnable);
        cacheFixedThreadPool.execute(runnable1);
        cacheFixedThreadPool.execute(runnable2);
        cacheFixedThreadPool.execute(runnable3);
    }

    private void runScheduleExecutor() {
        delaryFixedThreadPool = Executors.newScheduledThreadPool(2);
//        delaryFixedThreadPool.schedule(runnable, 2000, TimeUnit.MILLISECONDS);
//        delaryFixedThreadPool.schedule(runnable1, 2000, TimeUnit.MILLISECONDS);
//        delaryFixedThreadPool.schedule(runnable2, 2000, TimeUnit.MILLISECONDS);
//        delaryFixedThreadPool.schedule(runnable3, 2000, TimeUnit.MILLISECONDS);
        delaryFixedThreadPool.scheduleAtFixedRate(runnable, 10, 1000, TimeUnit.MILLISECONDS);
    }

    private void runSingleExecutor() {

        ExecutorService newSingleThreadPool = Executors.newSingleThreadExecutor();
        newSingleThreadPool.execute(runnable);
        newSingleThreadPool.execute(runnable1);
        newSingleThreadPool.execute(runnable2);
        newSingleThreadPool.execute(runnable3);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        delaryFixedThreadPool.shutdown();
    }
}
