package com.wynne.knowledge.mark.jetpack

import android.content.Intent
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R

class JetPackActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.jetpack_activity

    override fun initView() {

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_paging -> {
                startActivity(Intent(this, PagingActivity::class.java))
                return
            }
        }
    }

}