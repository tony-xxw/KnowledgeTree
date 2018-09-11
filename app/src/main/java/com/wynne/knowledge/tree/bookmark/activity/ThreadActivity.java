package com.wynne.knowledge.tree.bookmark.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;
import com.wynne.knowledge.tree.service.MyIntentService;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author xxw
 */
public class ThreadActivity extends BaseActivity implements View.OnClickListener {

    static class MyHandler extends Handler {
        WeakReference<Activity> weakReference;

        public MyHandler(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Activity activity = weakReference.get();
        }
    }

    private MyHandler mHandlers = new MyHandler(this);

    @Override
    public void initView() {
//        initViewStub();
        ViewStub thread = findViewById(R.id.vs_thread);
        thread.inflate();
        findViewById(R.id.btn_async).setOnClickListener(this);
        findViewById(R.id.btn_handler).setOnClickListener(this);
        findViewById(R.id.btn_service).setOnClickListener(this);
        findViewById(R.id.btn_executor).setOnClickListener(this);
        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                mHandlers.sendEmptyMessage(1);
            }
        });
    }

    private void initViewStub() {
        if (Math.random() > 0.5f) {
            ViewStub image = findViewById(R.id.vs_image);
            image.inflate();
        } else {
            ViewStub text = findViewById(R.id.vs_text);
            text.inflate();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.thread_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_async:
                new MyAsyncTask().execute("Thead");
                break;
            case R.id.btn_handler:
                initHandlerThread();
                break;
            case R.id.btn_service:
                initService();
                break;
            case R.id.btn_executor:
                initExecutor();
                break;
            default:
                break;
        }
    }

    private void initExecutor() {
        //核心线程数与总线程相等, 没有超时概念，响应更快
        ExecutorService newFixed = Executors.newFixedThreadPool(4);
        newFixed.execute(runnable);

        //没有核心线程数, 当闲置线程没有超时时,则用闲置线程处理,超时则创建新线程
        ExecutorService newCached = Executors.newCachedThreadPool();
        newCached.execute(runnable);

        //核心线程固定, 非核心线程无限大,没有闲置时间,当核心线程满了 则调用核心线程,用完则销毁
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(4);
        scheduled.execute(runnable);

        //单利线程 核心线程为1
        ExecutorService single = Executors.newSingleThreadExecutor();
        single.execute(runnable);
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(3000);
        }
    };

    private void initService() {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("task_action", "action1");
        startService(intent);
        intent.putExtra("task_action", "action2");
        startService(intent);
        intent.putExtra("task_action", "action3");
        startService(intent);

    }

    private void initHandlerThread() {
        HandlerThread handlerThread = new HandlerThread("11");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("XXW", Thread.currentThread().getName() + " : 2");
            }
        };
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("XXW", Thread.currentThread().getName());
                handler.sendEmptyMessage(1);
            }
        });
        handlerThread.quitSafely();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    static class MyAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {

            return strings.length + "";
        }
    }
}
