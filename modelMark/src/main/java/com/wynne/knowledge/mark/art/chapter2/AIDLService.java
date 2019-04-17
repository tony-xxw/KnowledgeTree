package com.wynne.knowledge.mark.art.chapter2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.wynne.knowledge.mark.IDemandListener;
import com.wynne.knowledge.mark.IDemandManager;
import com.wynne.knowledge.mark.MessageBean;

/**
 * @author xxw
 */
public class AIDLService extends Service {

    RemoteCallbackList<IDemandListener> dList = new RemoteCallbackList<>();

    @Override
    public IBinder onBind(Intent intent) {
        return demandManager;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            demandManager.registerListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);

    }

    IDemandManager.Stub demandManager = new IDemandManager.Stub() {
        @Override
        public MessageBean getDemand() throws RemoteException {
            MessageBean deMand = new MessageBean("Hello 树先生", 1);
            return deMand;
        }

        @Override
        public void setDemandIn(MessageBean msg) throws RemoteException {
            Log.d("XXW", "setDemandIn 程序员: " + msg.toString());
        }

        @Override
        public void setDemandOut(MessageBean msg) throws RemoteException {
            Log.d("XXW", "setDemandOut 程序员: " + msg.toString());
            msg.setContent("我不想解释, 你给我继续");
            msg.setLevel(5);
        }

        @Override
        public void setDeamandInOut(MessageBean msg) throws RemoteException {
            Log.d("XXW", "setDemandOut 程序员: " + msg.toString());
            msg.setContent("颜色个i上粉色");
            msg.setLevel(3);
        }

        @Override
        public void registerListener(IDemandListener list) throws RemoteException {
            dList.register(list);
        }

        @Override
        public void unregisterListener(IDemandListener list) throws RemoteException {
            dList.unregister(list);
        }
    };


    IDemandListener.Stub listener =new IDemandListener.Stub() {
        @Override
        public void onDemandReceiver(MessageBean msg) throws RemoteException {

        }
    };



}
