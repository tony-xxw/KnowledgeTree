package com.wynne.http

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath

@Route(path = ARouterPath.BASE_HTTP)
class BaseHttpActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_http_layout
    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XXW", "重启了")
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_socket -> {
                startActivity(Intent(this, SocketActivity::class.java))
            }
            R.id.btnRestart -> {
                startService(Intent(this, Service::class.java))
            }
        }
    }
}