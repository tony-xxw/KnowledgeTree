package com.wynne.knowledge.guide.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Wynne on 2018/3/21.
 */

public class WebViewInterface {

    public WebViewInterface(Context contex) {
        this.mContext = contex;
    }

    Context mContext;

    @JavascriptInterface
    public void showToast(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }
}
