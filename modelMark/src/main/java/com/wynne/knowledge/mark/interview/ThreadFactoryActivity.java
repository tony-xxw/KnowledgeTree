package com.wynne.knowledge.mark.interview;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.utils.LogUtil;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.R2;
import com.wynne.knowledge.mark.activity.ThreadActivity;

import butterknife.OnClick;

/**
 * 线程相关知识点
 *
 * @author Wynne
 */
public class ThreadFactoryActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.thread_factory;
    }


    @OnClick({R2.id.btn_executor, R2.id.btn_handlerThread, R2.id.btn_intentService, R2.id.btn_synchronized})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.btn_executor:
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(100, 100);
                break;
            case R2.id.btn_handlerThread:
                break;
            case R2.id.btn_intentService:
                startActivity(new Intent(this, ThreadActivity.class));
                break;
            case R2.id.btn_synchronized:
                for (int i = 0; i < 3; i++) {
                    MyThread thread = new MyThread();
                    thread.start();
                }
                break;
            default:
                break;
        }
    }


    public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(integers);
            LogUtil.d("doInBackground: " + Thread.currentThread().getName());
            return "完成";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LogUtil.d("onPreExecute");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            LogUtil.d("onPostExecute :" + s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            LogUtil.d("onProgressUpdate :" + values);
        }


    }


    public class MyThread extends Thread {


        @Override
        public void run() {
            super.run();
            Sync.test();
        }
    }

    public static class Sync {

        /**
         * synchronized 锁住的是一个对象, 如果不是static 锁住的只是这个方法本身, 如果是static锁住的就是这个对象(全局锁)
         */
        public static synchronized void test() {

            LogUtil.d("开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtil.d("结束");
        }


    }
}
