package com.wynne.knowledge.mark.art

import android.content.Intent
import android.util.Log
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R
import com.wynne.knowledge.mark.activity.AnimationActivity
import com.wynne.knowledge.mark.activity.DrawableActivity
import com.wynne.knowledge.mark.art.charpter1.ChapterOneActivity
 import com.wynne.knowledge.mark.art.charpter1.UserManager
import com.wynne.knowledge.mark.art.charpter2.ChapterTwoActivity
import com.wynne.knowledge.mark.art.charpter3.ChapterViewActivity
import com.wynne.knowledge.mark.art.charpter8.WindowActivity

class ArtActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.art_activity

    override fun initView() {
        Log.d("XXW", "userId: " + UserManager.sUserId)

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_drawable -> {
                startActivity(Intent(this, DrawableActivity::class.java))
            }
            R.id.btn_anim -> {
                startActivity(Intent(this, AnimationActivity::class.java))
            }
            R.id.btnOneChapter -> {
                startActivity(Intent(this, ChapterOneActivity::class.java))
            }
            R.id.btn_view -> {
                startActivity(Intent(this, ChapterViewActivity::class.java))
            }
            R.id.btn_window -> {
                startActivity(Intent(this, WindowActivity::class.java))
            }
            R.id.btnTwoChapter -> {
                startActivity(Intent(this, ChapterTwoActivity::class.java))
            }


        }
    }
}