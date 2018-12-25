package com.wynne.knowledge.main.ipc.binder;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.aidl.sample.ICompute;
import com.aidl.sample.ISecurityCenter;
import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.main.custom.ipc.binder.ComputeImp;
import com.wynne.knowledge.main.custom.ipc.binder.SecurityCenterImp;

/**
 * @author Wynne
 * @date 2018/4/2
 */

public class BinderPoolActivity extends BaseActivity {

    private static final String TAG = "BinderPoolActivity";

    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;


    @Override
    public void initView() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                doWork();
            }
        }).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.binder_pool_activity;
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInsance(BinderPoolActivity.this);
        IBinder securityBinder = binderPool
                .queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = (ISecurityCenter) SecurityCenterImp
                .asInterface(securityBinder);
        Log.d(TAG, "visit ISecurityCenter");
        String msg = "helloworld-安卓";
        System.out.println("content:" + msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            System.out.println("encrypt:" + password);
            System.out.println("decrypt:" + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "visit ICompute");
        IBinder computeBinder = binderPool
                .queryBinder(BinderPool.BINDER_COMPUTE);
        ;
        mCompute = ComputeImp.asInterface(computeBinder);
        try {
            System.out.println("3+5=" + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}