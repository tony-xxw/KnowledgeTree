package com.wynne.android.lifecycle

import android.util.Log
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity

class LifeActivity : BaseActivity() {
    lateinit var presenter: IPresenter

    override fun initView() {
        Log.d("XXW2", "onCreate ${this.javaClass.name}")
        presenter = LifePresenter()
        lifecycle.addObserver(presenter)
    }

    override fun getLayoutId(): Int = R.layout.actiivty_life_layout

    override fun onDestroy() {
        super.onDestroy()
        Log.d("XXW2", "onDestroy: ${this.javaClass.name}")

    }

}