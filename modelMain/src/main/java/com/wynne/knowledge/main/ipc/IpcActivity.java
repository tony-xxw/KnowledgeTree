package com.wynne.knowledge.main.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.aidl.sample.Book;
import com.aidl.sample.IBookManager;
import com.aidl.sample.IOnNewBookArrivedListener;
import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;


import java.util.List;

/**
 * Binder
 * 从Framework层来说 是ServiceManager连接各种Manager(ActivityManager,WindowManager)和相应ManagerService的桥梁
 * 从应用层来说,Binder是客户端和服务端进行通信媒介
 *
 * @author Wynne
 * @date 2018/3/22
 */

public class IpcActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void initView() {
        findViewById(R.id.test_remote).setOnClickListener(this);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ipc_activity;
    }


    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("XXW", "newBook " + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private IBookManager iBookManager;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager manager = IBookManager.Stub.asInterface(service);
            try {
                iBookManager = manager;
                List<Book> list = manager.getBookList();
                Log.d("XXW", "query book list,list type " + list.getClass().getCanonicalName());
                Log.d("XXW", "query book list:" + list.toString());
                Book newBook = new Book(3, "Android艺术探索");
                iBookManager.addBook(newBook);
                List<Book> newList = manager.getBookList();
                Log.d("XXW", "query book list:" + newList.toString());
                manager.registerListener(listener);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iBookManager = null;
        }
    };


    IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandle.obtainMessage(1, newBook).sendToTarget();
        }
    };


    @Override
    protected void onDestroy() {

        if (iBookManager != null && iBookManager.asBinder().isBinderAlive()) {
            try {
                iBookManager.unregisterListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(connection);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.test_remote) {
            try {
                iBookManager.getBookList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        } else {
        }
    }

}
