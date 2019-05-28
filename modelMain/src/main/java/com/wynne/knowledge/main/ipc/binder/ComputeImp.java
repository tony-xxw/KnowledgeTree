package com.wynne.knowledge.main.ipc.binder;

import android.os.RemoteException;

import com.aidl.sample.ICompute;

/**
 * @author Wynne
 * @date 2018/4/2
 */

public class ComputeImp extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
