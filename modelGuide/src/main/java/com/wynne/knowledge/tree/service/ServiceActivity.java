package com.wynne.knowledge.tree.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

/**
 * @author Wynne
 * @date 2018/3/13
 */

public class ServiceActivity extends BaseActivity {
    private Intent intent;
    private Intent bindIntent;
    private BindService bindService;
    private boolean mBinder = false;
    private Messenger mMessenger;


    @Override
    public void initView() {
        intent = new Intent(this, StandardService.class);
        bindIntent = new Intent(this, BindService.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity;
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
        bindService(new Intent(this, BindService.class), messager,
                BIND_AUTO_CREATE);
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_start) {
            startService(intent);


        } else if (i == R.id.btn_stop) {
            stopService(intent);

        } else if (i == R.id.btn_bind) {
            if (mBinder) {
                sayHello(view);
            }

        } else if (i == R.id.btn_unbind) {
        } else {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder) {
            unbindService(connection);
            mBinder = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.LocalBinder localBinder = (BindService.LocalBinder) service;
            localBinder.getService();
            bindService = localBinder.getService();
            mBinder = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBinder = false;
        }
    };

    private ServiceConnection messager = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            mBinder = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
            mBinder = false;
        }
    };

    public void sayHello(View v) {
        if (!mBinder) {
            return;
        }
        Message msg = Message.obtain(null, BindService.MSG_SAY_HELLO, 0, 0);
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
