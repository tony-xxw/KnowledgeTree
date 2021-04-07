package com.wynne.other.coroutine

import android.util.Log
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.other.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoroutineActivity : BaseActivity() {
    override fun initView() {
        val obser = Observable.create<String> {
            Log.d("xxw", Thread.currentThread().name)
            it.onNext("xxw")
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        val subscribe = obser.subscribe {
            Log.d("xxw", Thread.currentThread().name)
        }

    }

    override val layoutId: Int = R.layout.activity_coroutine_layout


}