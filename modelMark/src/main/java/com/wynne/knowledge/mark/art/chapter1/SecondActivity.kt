package com.wynne.knowledge.mark.art.chapter1

import android.content.Intent
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R

class SecondActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.second

    override fun initView() {

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_intent -> {
                startActivity(Intent(SecondActivity@ this, ChapterOneActivity::class.java));
                return
            }
        }
    }
}