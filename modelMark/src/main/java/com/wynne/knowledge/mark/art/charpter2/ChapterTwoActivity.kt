package com.wynne.knowledge.mark.art.charpter2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R
import com.wynne.knowledge.mark.art.charpter1.SecondActivity

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


    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_intent -> {
//

                startActivity(Intent(ChapterTwoActivity@ this, SecondActivity::class.java))
                return
            }
        }
    }
}