package com.wynne.knowledge.tree.custom.thread;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wynne.knowledge.tree.R;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池Demo
 *
 * @author Wynne
 * @date 2018/5/21
 */

public class ThreadPoolActivity extends AppCompatActivity {
    private ThreadPoolExecutor executor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_pool_activity);


        initThreadPool();
        initNewSingerThreadPool();
        initNewFixedThreadPool();
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
        ExecutorService executors = Executors.newFixedThreadPool(10);
    }

    private void initThreadPool() {

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
