package com.wynne.knowledge.home.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

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
    public void onStart(Intent intent, int startId) {
        Log.d("XXW", "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("XXW", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(  Intent intent) {
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
