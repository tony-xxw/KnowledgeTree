package com.wynne.android.custom

import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_custom_layout.*

class AndroidCustomActivity : BaseActivity() {
    override fun initView() {
        tlBar.title="自定义控件"
        val list= mutableListOf<String>()
        list.add("201")
        list.add("美国")
        list.add("科幻")
        list.add("动作")
        list.add("探险")
        list.add("3D")
        list.add("恐怖")
        list.add("惊悚")
        flLayout.setTags(list)
    }

    override fun getLayoutId(): Int = R.layout.activity_base_custom_layout


}