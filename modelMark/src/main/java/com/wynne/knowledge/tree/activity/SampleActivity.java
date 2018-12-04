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
 * @author XXW
 * @date 2018/3/11
 */

public class SampleActivity extends BaseActivity {
    public static final String TAG = SampleActivity.class.getSimpleName();


    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.sample_activity;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_result:
                Intent intent = new Intent();
                intent.putExtra(TAG, "result");
                setResult(RESULT_OK);
                finish();
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
}
