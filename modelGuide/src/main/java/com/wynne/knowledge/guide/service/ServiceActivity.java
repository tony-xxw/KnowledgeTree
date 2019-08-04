package com.wynne.knowledge.guide.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.guide.R;

/**
 * @author Wynne
 * @date 2018/3/13
 */

public class ServiceActivity extends BaseActivity {
    private Intent intent;
    private TextView textView;


    @Override
    public void initView() {
        textView = findViewById(R.id.tvTest);
        intent = new Intent(this, StandardService.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity;
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_start) {
            startService(intent);
        } else if (i == R.id.btn_stop) {
            stopService(intent);
        } else if (i == R.id.btn_bind) {
            bindService(intent, connection, BIND_AUTO_CREATE);
        } else if (i == R.id.btn_unbind) {
            unbindService(connection);
        } else if (i == R.id.btn_unbind_stop) {
            unbindService(connection);
            stopService(intent);
        }
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("XXW", "current Thread  onServiceConnected  " + Thread.currentThread().getName());
            StandardService.MyBinder binder = (StandardService.MyBinder) service;
            binder.bindService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}
