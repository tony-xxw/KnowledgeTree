package com.wynne.android.fragment

import android.util.Log
import com.wynne.android.R
import com.wynne.knowledge.base.base.view.BaseFragment

/**
 * TODO 类描述
 *
 * @author HB.xiangxianwen
 * @date 2022-09-15
 */
class LazySecondFragment :BaseFragment(){
    override fun initView() {

    }

    override fun getLayoutId(): Int = R.layout.fragment_second_lazy_layout

    override fun onResume() {
        super.onResume()
        Log.d("XXW","LazySecond onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW","LazySecond onPause")
    }
}