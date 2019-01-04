package com.wynne.knowledge.mark.art

import android.content.Intent
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R
import com.wynne.knowledge.mark.activity.AnimationActivity
import com.wynne.knowledge.mark.activity.DrawableActivity
import com.wynne.knowledge.mark.art.charpter1.ChapterOneActivity

class ArtActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.art_activity

    override fun initView() {

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

        }
    }
}