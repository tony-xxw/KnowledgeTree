package com.wynne.other.edit

import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.other.R
import kotlinx.android.synthetic.main.activity_edit_input_other_layout.*

class OtherInputEditActivity : BaseActivity() {
    override fun initView() {
        toolBar.title = "软键盘相关"


    }

    override fun getLayoutId(): Int = R.layout.activity_edit_input_other_layout
}