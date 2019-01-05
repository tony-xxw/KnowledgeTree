package com.wynne.knowledge.mark.activity

import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R
import kotlinx.android.synthetic.main.animation_path_activity.*

class AnimationPathActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.animation_path_activity

    override fun initView() {
        val vector = AnimatedVectorDrawableCompat.create(this@AnimationPathActivity, R.drawable.line_animated_svg)
        ivVector.setImageDrawable(vector)
        vector?.start()
    }
}