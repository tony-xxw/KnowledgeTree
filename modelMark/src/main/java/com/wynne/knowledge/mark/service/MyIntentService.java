package com.wynne.knowledge.mark.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * @author xxw
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
    public void onStart( Intent intent, int startId) {
        Log.d("XXW", "onStart :       startId :" + startId);
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId) {
        String action = intent.getStringExtra("task_action");
        Log.d("XXW", "onStartCommand : " + action);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        String action = intent.getStringExtra("task_action");
        SystemClock.sleep(3000);

        Log.d("XXW", "onHandleIntent : task  " + action);

        if ("action1".equals(action)) {
            Log.d("XXW", "onHandleIntent : " + action);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("XXW", "onDestroy");
    }
}

