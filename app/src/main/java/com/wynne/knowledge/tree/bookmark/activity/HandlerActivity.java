package com.wynne.knowledge.tree.bookmark.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/7/30
 */

public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_layout);
    }

    public void onHandler(View v) {
        switch (v.getId()) {
            case R.id.btn_handler:
                break;
            default:
                break;
        }
    }
}
