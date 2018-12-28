package com.wynne.knowledge.guide.constrain

import android.content.Intent
import android.util.Log
import android.widget.TextView
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.guide.R
import com.wynne.knowledge.guide.material.RecycleActivity

/**
 * @author XXW
 * @date 2018/3/4
 */

class ConstraintActivity : BaseActivity() {
    private lateinit var tvTitle: TextView

    override fun initView() {
        tvTitle = findViewById(R.id.tv_follow)
        tvTitle.setOnClickListener {
            Log.d("XXW", "哈哈哈")
        }

        startActivity(Intent(this, RecycleActivity::class.java))
    }

    override fun getLayoutId(): Int {
        return R.layout.constrain_layout
    }
}
