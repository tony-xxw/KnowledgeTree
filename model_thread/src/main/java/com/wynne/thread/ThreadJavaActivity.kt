package com.wynne.thread

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.adapter.MainAdapter
import com.wynne.knowledge.base.adapter.MainData
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_THREAD
import kotlinx.android.synthetic.main.activity_thread_android_layout.*
import kotlinx.android.synthetic.main.activity_thread_android_layout.toolBar
import kotlinx.android.synthetic.main.activity_thread_java_layout.*

@Route(path = BASE_THREAD)
class ThreadJavaActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_thread_java_layout
 
    lateinit var adapter: MainAdapter
    var list = mutableListOf(
            MainData("线程池", R.drawable.icon_grape),
            MainData("AsyncTask", R.drawable.icon_apple),
            MainData("IntentService", R.drawable.icon_watermelon),
            MainData("HandlerThread", R.drawable.icon_peach),
            MainData("多进程通信Binder,AIDL,多进程", R.drawable.icon_pear))

    override fun initView() {
        toolBar.title="知识体系"
        adapter = MainAdapter(this)
        adapter.mList = list

        rvJava.adapter = adapter
    }

}