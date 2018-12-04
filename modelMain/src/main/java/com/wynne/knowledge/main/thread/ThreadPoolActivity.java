package com.wynne.knowledge.main.thread;


import android.os.SystemClock;
import android.util.Log;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池Demo
 *
 * @author Wynne
 * @date 2018/5/21
 */

public class ThreadPoolActivity extends BaseActivity {
    private ThreadPoolExecutor executor;


    @Override
    public void initView() {
        initThreadPool();
//        initNewSingerThreadPool();
//        initNewFixedThreadPool();}
    }

    @Override
    public int getLayoutId() {
        return R.layout.thread_pool_activity;
    }

    private void initNewFixedThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                200,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            Log.d("XXW", "线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
    }

    private void initNewSingerThreadPool() {


        ThreadPoolExecutor newFixed = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            newFixed.execute(myTask);
            Log.d("XXW", "线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
    }

    private void initThreadPool() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(runnable);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(runnable);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(runnable, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(runnable, 10, 1000, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(runnable);


    }


    class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            Log.d("XXW", "正在执行task " + taskNum);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("XXW", "task " + taskNum + "执行完毕");
        }
    }
}
