package com.wynne.knowledge.tree.guide.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wynne.knowledge.tree.R;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Wynne
 * @date 2018/3/13
 */

public class ServiceActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        intent = new Intent(this, StandardService.class);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startService(intent);

                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
            case R.id.btn_bind:
                break;
            case R.id.btn_unbind:
                break;
            default:
                break;
        }
    }
}
