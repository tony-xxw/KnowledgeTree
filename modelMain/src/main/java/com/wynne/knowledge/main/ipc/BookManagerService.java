package com.wynne.knowledge.main.ipc;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aidl.sample.Book;
import com.aidl.sample.IBookManager;
import com.aidl.sample.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Wynne
 * @date 2018/3/22
 */

public class BookManagerService extends Service {

    private static final String TAG = "BMS";

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListeners = new CopyOnWriteArrayList<>();
    /**
     * 用来保存跨进程的回调
     */
    private RemoteCallbackList<IOnNewBookArrivedListener> mRemoteCallback = new RemoteCallbackList<>();
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //还可以通过在onTransact方法中进行权限验证,如果不符合则直接fasle
        int check = checkCallingOrSelfPermission("com.wynne.knowledge.tree.permission.ACCESS_BOOK_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED) {
            return null;
        }

        return mBinder;
    }

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            SystemClock.sleep(5000);
            Log.d("XXW", "addBook ==" + Thread.currentThread().getName());
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);

        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (!mListeners.contains(listener)) {
//                mListeners.add(listener);
//            }
            mRemoteCallback.register(listener);


        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            /**if (mListeners.contains(listener)) {
             mListeners.remove(listener);
             }*/
            mRemoteCallback.unregister(listener);

            Log.d("XXW", "unregisterListener===" + mRemoteCallback.beginBroadcast());
        }


        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int check = checkCallingOrSelfPermission("com.wynne.knowledge.tree.permission.ACCESS_BOOK_SERVICE");
            //通过PERMISSION_DENIED
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }
            //通过UID与PID

            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }

            if (!packageName.startsWith("com.wynne")) {
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("XXW", "onCreate  Service");
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "IOS"));
        new Thread(new ServiceWorker()).start();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "newBook#" + bookId);
                onNewBookArrived(newBook);

            }
        }


    }

    private void onNewBookArrived(Book newBook) {
        mBookList.add(newBook);

        /**for (int i = 0; i < mListeners.size(); i++) {
         IOnNewBookArrivedListener listener = mListeners.get(i);
         try {
         listener.onNewBookArrived(newBook);
         } catch (RemoteException e) {
         e.printStackTrace();
         }
         }*/

        final int N = mRemoteCallback.beginBroadcast();
        Log.d("XXW", "registerListener===" + N);
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener listener = mRemoteCallback.getBroadcastItem(i);

            if (listener != null) {
                try {
                    listener.onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mRemoteCallback.finishBroadcast();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }
}
