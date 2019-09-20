package com.wynne.knowledge.guide.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.wynne.knowledge.base.utils.StringUtils;

import androidx.annotation.Nullable;

/**
 * 1.start再Bind一个Service ,如何停止一个Service
 * 需要同时调用unBinder和onStop Service的onDestroy才会生效
 * 2.Service onStartCommand的返回值
 * 3.bindService后,ServiceConnection里面的回调方法运行在哪个线程？它们的调用时机分别是什么？
 * 主线程
 * 4.Service的onCreate运行在哪个线程？
 * 主线程
 *
 * @author Wynne
 * @date 2018/3/13
 */

public class StandardService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        StringUtils.d("XXW", "onCreate");
        runtime("onCreate");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        StringUtils.d("XXW", "onStartCommand");
        runtime("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        StringUtils.d("XXW", "onBind");
        runtime("onBind");
        return new MyBinder();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        StringUtils.d("XXW", "onDestroy");
        runtime("onDestroy");
    }


    class MyBinder extends Binder {

        public void bindService() {
            Log.d("XXW", "BindService");
        }
    }


    public void runtime(String methodName) {
        Log.d("XXW", "current Thread " + methodName + " ---- " + Thread.currentThread().getName());
    }
}
