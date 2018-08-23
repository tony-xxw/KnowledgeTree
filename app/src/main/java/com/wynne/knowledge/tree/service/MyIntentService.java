package com.wynne.knowledge.tree.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author Wynne
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("myIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        SystemClock.sleep(2000);

        Log.d("XXW", "onHandleIntent : task  " + action);

        if ("action3".equals(action)) {
            Log.d("XXW", "onHandleIntent : " + action);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("XXW", "onDestroy");
    }
}
