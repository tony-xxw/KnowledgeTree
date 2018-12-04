package com.wynne.knowledge.main.ipc.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author Wynne
 * @date 2018/4/2
 */

public class BinderPoolService extends Service {

    private static final String TAG = "BinderPoolService";
    private Binder mBinderPool = new BinderPool.IBinderPoolImp();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinderPool;
    }


}
