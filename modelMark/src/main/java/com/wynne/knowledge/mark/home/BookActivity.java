package com.wynne.knowledge.mark.home;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.mark.R;

/**
 * @author xxw
 */
@Route(path = ARouterPath.FRAGMENT_BOOKMARK)
public class BookActivity extends BaseActivity {
    private String ab = "ab";
    private String cd = "cd";
    private String abcd = ab + cd;
    private String[] strings = {"1", "3", "4", "2", "5"};
    private String change ;

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
                if (Integer.valueOf(strings[i])<Integer.valueOf(strings[j])){
                    change = strings[j];
                    strings[j]= strings[i];
                    strings[i] =change;
                }
            }
        }
        for (String string : strings) {
            Log.d("XXW",string);
        }


    run();

    }

    public static void run(){
        ((A)new B()).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.book_activity;
    }


    public static class A {
        void start(){
            Log.d("XXW","A");
        }
    }

    public static class B extends A{
        void start(){
            Log.d("XXW","B");
        }
    }
}
