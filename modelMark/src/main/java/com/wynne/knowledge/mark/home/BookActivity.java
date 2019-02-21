package com.wynne.knowledge.mark.home;

import android.util.Log;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

/**
 * @author xxw
 */
public class BookActivity extends BaseActivity {
    private String ab = "ab";
    private String cd = "cd";
    private String abcd = ab+cd;

    @Override
    public void initView() {
        Log.d("XXW", ab.toString());
        Log.d("XXW", abcd.toString());
    }

    @Override
    public int getLayoutId() {
        return R.layout.book_activity;
    }
}
