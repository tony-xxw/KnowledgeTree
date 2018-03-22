package com.wynne.knowledge.tree.custom.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aidl.sample.Book;
import com.aidl.sample.IBookManager;
import com.wynne.knowledge.tree.R;

import java.util.List;

/**
 * Binder
 * 从Framework层来说 是ServiceManager连接各种Manager(ActivityManager,WindowManager)和相应ManagerService的桥梁
 * 从应用层来说,Binder是客户端和服务端进行通信媒介
 *
 * @author Wynne
 * @date 2018/3/22
 */

public class IpcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipc_activity);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager manager = IBookManager.Stub.asInterface(service);
            try {
                List<Book> list = manager.getBookList();
                Log.d("XXW", "query book list,list type " + list.getClass().getCanonicalName());
                Log.d("XXW", "query book list:" + list.toString());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
