package com.wynne.knowledge.mark.art.chapter2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.IDemandManager;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.R2;

import butterknife.OnClick;

/**
 * @author xxw
 */
public class ChapterTwoActivity extends BaseActivity {
    @Override
    public void initView() {
        Intent intent = new Intent();
        intent.setAction("com.wynne.aidl");
        intent.setPackage("com.wynne.knowledge.mark");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private IDemandManager demandManager;

    @Override
    public int getLayoutId() {
        return R.layout.chapter_two_activity;
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            demandManager = IDemandManager.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @OnClick({R2.id.btn_intent})
    public void onClick(View view) {
        if (view.getId() == R2.id.btn_intent) {
            try {
                Log.d("XXW", "toString " + demandManager.getDemand().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }
}
