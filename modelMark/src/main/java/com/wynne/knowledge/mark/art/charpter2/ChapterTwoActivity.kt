package com.wynne.knowledge.mark.art.charpter2

import android.util.Log
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R

class ChapterTwoActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.chapter_two_activity

    override fun initView() {

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("XXW", "ChapterTwoActivity : onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("XXW", "ChapterTwoActivity :onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("XXW", "ChapterTwoActivity :onResume")
    }
}