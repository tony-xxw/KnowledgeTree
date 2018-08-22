package com.wynne.knowledge.tree.bookmark.activity;

import android.view.ViewStub;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

/**
 * @author xxw
 */
public class ThreadActivity extends BaseActivity {
    @Override
    public void initView() {
        if (Math.random() > 0.5f) {
            ViewStub image = findViewById(R.id.vs_image);
            image.inflate();
        } else {
            ViewStub text = findViewById(R.id.vs_text);
            text.inflate();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.thread_activity;
    }
}
