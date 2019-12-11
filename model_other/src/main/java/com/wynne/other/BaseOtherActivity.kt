package com.wynne.other

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath
import com.wynne.other.edit.OtherInputEditActivity
import kotlinx.android.synthetic.main.activity_base_other_layout.*

@Route(path = ARouterPath.BASE_OTHER)
class BaseOtherActivity : BaseActivity() {
    override fun initView() {
        toolBar.title = "其他"
    }

    override fun getLayoutId(): Int = R.layout.activity_base_other_layout

    fun onClick(v: View) {
        when (v.id) {
            R.id.btnEditInput -> {
                startActivity(Intent(this, OtherInputEditActivity::class.java))
            }
        }
    }
}