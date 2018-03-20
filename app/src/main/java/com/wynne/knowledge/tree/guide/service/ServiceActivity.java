package com.wynne.knowledge.tree.guide.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wynne.knowledge.tree.R;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Wynne
 * @date 2018/3/13
 */

public class ServiceActivity extends AppCompatActivity {
    private Intent intent;
    private Intent bindIntent;
    private BindService bindService;
    private boolean mBinder = false;
    private Messenger mMessenger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        intent = new Intent(this, StandardService.class);
        bindIntent = new Intent(this, BindService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        bindService(bindIntent, connection, BIND_AUTO_CREATE);
        bindService(new Intent(this, BindService.class), messager,
                BIND_AUTO_CREATE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startService(intent);

                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
            case R.id.btn_bind:
                if (mBinder) {
//                    Toast.makeText(this, bindService.getRandomNumber() + "", Toast.LENGTH_LONG).show();
                    sayHello(view);
                }
                break;
            case R.id.btn_unbind:
                break;
            default:
                break;
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
