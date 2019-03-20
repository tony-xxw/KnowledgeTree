package com.wynne.knowledge.mark.art.charpter1

import android.content.Intent
import android.util.Log
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R

class ChapterTwoActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.chapter_two_activity

    override fun initView() {

        var intent = Intent()
        intent.action = "com.wynne.aidl"
        intent.setPackage("com.wynne.knowledge.mark")
    }

}