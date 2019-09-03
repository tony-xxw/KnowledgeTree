package com.wynne.knowledge.mark.home;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.mark.R;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xxw
 */
@Route(path = ARouterPath.FRAGMENT_BOOKMARK)
public class BookActivity extends BaseActivity {
    private String ab = "ab";
    private String cd = "cd";
    private String abcd = ab + cd;
    private String[] strings = {"1", "3", "4", "2", "5"};
    private String change;

    @Override
    public void initView() {
        Log.d("XXW", ab.toString());
        Log.d("XXW", abcd.toString());
        Log.d("XXW", 0x300 + " 十六进制");
        int a = 2 & 1;
        int ab = 2 | 1;
        Log.d("XXW", a + " 十六进制 " + ab);

        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (Integer.valueOf(strings[i]) < Integer.valueOf(strings[j])) {
                    change = strings[j];
                    strings[j] = strings[i];
                    strings[i] = change;
                }
            }
        }
        for (String string : strings) {
            Log.d("XXW", string);
        }


        run();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Socket socket = new Socket("192.168.1.172", 8989);
                    Log.d("XXW", "socket isConnect " + socket.isConnected());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void run() {
        B b = new B();
        b.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.book_activity;
    }


    public class A {
        void start() {
            Log.d("XXW", "A");
        }

        void subsribeOn() {
            subsribe(this);
        }

        void subsribe(A a) {
            Log.d("XXW", "" + a.getClass().getName());
        }

        void getType(){
            Log.d("XXW","A");
        }
    }

    public class B extends A {
        @Override
        void start() {
            Log.d("XXW", "B");
            subsribeOn();
        }

        void getType(){

            Log.d("XXW","B");
        }

    }


}
