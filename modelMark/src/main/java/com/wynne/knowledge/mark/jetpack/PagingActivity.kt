package com.wynne.knowledge.mark.jetpack

import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R

class PagingActivity : BaseActivity() {
    val asc = Array(5, { i -> (i * i).toString() })
    var mList: List<String>? = null

    override fun getLayoutId(): Int = R.layout.paging_activity

    override fun initView() {

    }
}