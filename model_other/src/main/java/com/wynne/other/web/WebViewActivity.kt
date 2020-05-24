package com.wynne.other.web

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.other.R
import kotlinx.android.synthetic.main.activity_webview_layout.*

class WebViewActivity : BaseActivity() {


    override fun initView() {
        webView.loadUrl("http://192.168.0.100:18000/wechat-2haohr/fadada-callback.html")
        webView.webChromeClient = WebChromeClients()
    }

    override fun getLayoutId(): Int = R.layout.activity_webview_layout

    class WebChromeClients : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            Log.d("xxw", "newProgress: $newProgress")
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            Log.d("xxw", title)
        }
    }
}