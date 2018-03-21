package com.wynne.knowledge.tree.guide.webview;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/3/21
 */

public class WebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private WebSettings mWebSetting;
    PackageInfo webInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        mWebView = (WebView) findViewById(R.id.wv_sample);
        mWebView.loadUrl("http://xiangxianwen.me/");
        //webInfo = mWebView.getCurrentWebViewPackage();  7.0开始可以 选择不同版本的Webview
        mWebSetting = mWebView.getSettings();
        //启动JavaScript
        mWebSetting.setJavaScriptEnabled(true);
        mWebSetting.setUserAgentString("");
        mWebView.addJavascriptInterface(new WebViewInterface(this), "Android");
        mWebView.setWebViewClient(new MyWebClient());
    }


    public class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("xiangxianwen.me")) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
