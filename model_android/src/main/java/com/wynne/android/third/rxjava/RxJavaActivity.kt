package com.wynne.android.third.rxjava

import android.util.Log
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.actiivty_rxjava_layout.*

class RxJavaActivity : BaseActivity() {


    override fun initView() {
        Observable.create<String> {
            Log.d("XXW","ThreadName1: ${Thread.currentThread()}")
            it.onNext("1")
            it.onNext("2")
            it.onNext("3")
            it.onComplete()
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d("XXW", "onSubscribe: $d")
                        Log.d("XXW","ThreadName2: ${Thread.currentThread()}")

                    }

                    override fun onNext(t: String) {
                        Log.d("XXW","ThreadName3: ${Thread.currentThread()}")
                        Log.d("XXW", "onNext: $t")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("XXW", "onError: $e")
                    }

                    override fun onComplete() {
                        Log.d("XXW", "onComplete")
                    }

                })


    }


    override val layoutId: Int = R.layout.actiivty_rxjava_layout
}