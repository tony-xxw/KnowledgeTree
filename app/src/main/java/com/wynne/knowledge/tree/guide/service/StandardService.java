package com.wynne.knowledge.tree.guide.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.wynne.knowledge.tree.Utils;

/**
 * @author Wynne
 * @date 2018/3/13
 */

public class StandardService extends Service {
    public static final String TAG = StandardService.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.d(TAG, "onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Utils.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Utils.d(TAG, "onBind");
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Utils.d(TAG, "onDestroy");
    }
}
