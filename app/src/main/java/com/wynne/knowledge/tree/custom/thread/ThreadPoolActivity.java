package com.wynne.knowledge.tree.custom.thread;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wynne.knowledge.tree.R;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
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
    }

    private void initThreadPool() {
        executor = new ThreadPoolExecutor(
                2,
                2,
                30,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        return null;
                    }
                }
        );


        /**executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("XXW", "runnable");
            }
        });*/
    }
}
