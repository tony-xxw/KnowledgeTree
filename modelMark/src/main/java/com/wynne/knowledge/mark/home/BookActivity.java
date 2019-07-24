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

    @Override
    public void initView() {
        Log.d("XXW", ab.toString());
        Log.d("XXW", abcd.toString());
        Log.d("XXW", 0x300 + " 十六进制");
        int a = 2 & 1;
        int ab = 2 | 1;
        Log.d("XXW", a + " 十六进制 " + ab);
    }

    @Override
    public int getLayoutId() {
        return R.layout.book_activity;
    }
}
