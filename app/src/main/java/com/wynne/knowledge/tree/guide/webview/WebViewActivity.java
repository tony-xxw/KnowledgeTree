package com.wynne.knowledge.tree.guide.webview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.wynne.knowledge.tree.MainActivity;
import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/3/21
 */

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWebView;
    private WebSettings mWebSetting;
    PackageInfo webInfo;


    private String webLocal = "file:///android_asset/javascript.html";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        findViewById(R.id.btn_call).setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.wv_sample);
        mWebView.loadUrl(webLocal);
        //webInfo = mWebView.getCurrentWebViewPackage();  7.0开始可以 选择不同版本的Webview
        mWebSetting = mWebView.getSettings();
        //启动JavaScript
        mWebSetting.setJavaScriptEnabled(true);
        mWebSetting.setUserAgentString("");
        mWebSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(new WebViewInterface(this), "Android");
        mWebView.setWebViewClient(new MyWebClient());
        mWebView.addJavascriptInterface(new AndroidtoJs(), "test");
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                    }
                });
                break;
            default:
                break;
        }
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


    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void hello(String msg) {
            System.out.println("JS调用了Android的hello方法");
            Toast.makeText(WebViewActivity.this, "JS调用了Android的hello方法", Toast.LENGTH_LONG).show();
        }
    }
}
