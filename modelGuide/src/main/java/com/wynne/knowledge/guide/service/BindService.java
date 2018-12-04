package com.wynne.knowledge.guide.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

/**
 * 1.同进程的Binder
 * 2.Messenger
 * 3.AIDL
 *
 * @author Wynne
 * @date 2018/3/20
 */

public class BindService extends Service {
    private final Binder mBinder = new LocalBinder();
    private final Random mRandom = new Random();
    static final int MSG_SAY_HELLO = 1;


    private class LocalHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();

        return message.getBinder();
    }


    class LocalBinder extends Binder {

        BindService getService() {
            return BindService.this;
        }
    }


    public int getRandomNumber() {
        return mRandom.nextInt(100);
    }

    private Messenger message = new Messenger(new LocalHandle());
}
