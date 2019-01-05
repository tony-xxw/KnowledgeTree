package com.wynne.knowledge.mark.activity

import android.content.Intent
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R


class CustomActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.custom_view_activity

    override fun initView() {
    }


    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_anim_custom -> {
                startActivity(Intent(this, AnimationActivity::class.java))
                return
            }
            R.id.btn_anim_path -> {
                startActivity(Intent(this, AnimationPathActivity::class.java))
                return
            }
            R.id.btn_shadown -> {
                startActivity(Intent(this, ShadowActivity::class.java))
                return
            }
            R.id.btn_xfermode -> {
                startActivity(Intent(this, XFermodeActivity::class.java))
                return
            }
        }
    }
}