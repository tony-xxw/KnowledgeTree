package com.wynne.knowledge.tree.bookmark.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

/**
 * 1.Activity的四种启动模式 Stands singTop SingTask SingleInstance
 * 2.onSaveInstanceState(在异常终止情况下保存信息,如EditText 和View的状态等 在onPause之后调用
 * 3.onRestoreInstanceState (在异常终止情况下获取保存信息,在onStar之后调用
 *
 * @author XXW
 * @date 2018/3/11
 */

public class TaskActivity extends BaseActivity {
    public static final String TAG = TaskActivity.class.getSimpleName();
    public static final int REQUEST = 100;


    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.task_activity;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_task:
//                startActivityForResult(new Intent(this, SampleActivity.class), REQUEST);
                Intent intent = new Intent(this, TaskActivity.class);
                intent.putExtra("time", System.currentTimeMillis());
                startActivity(intent);
                break;
            case R.id.btn_filter:
                Intent filter = new Intent();
                filter.setAction("com.wynne.activity.baskactivity");
                filter.addCategory("com.wynne.activity.category.a");
                startActivity(filter);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("XXW", TAG + "====onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("XXW", TAG + "====onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("XXW", TAG + "====onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("XXW", TAG + "====onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("XXW", TAG + "====onPause");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("XXW", TAG + "====onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("XXW", TAG + "====onRestoreInstanceState");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULT_OK) {
            String result = data.getStringExtra(SampleActivity.TAG);
            Log.d("XXW", "result---" + result);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent,time" + intent.getLongExtra("time", 0));
    }
}
