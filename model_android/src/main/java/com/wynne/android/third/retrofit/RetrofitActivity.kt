package com.wynne.android.third.retrofit

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : BaseActivity() {
    lateinit var disposable: Disposable

    override fun initView() {

        val build = Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val create = build.create(RetrofitService::class.java)
        disposable = create.obtainDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.data.forEach { item ->
                        Log.d("XXW", item.name)
                    }
                }, {
                    Log.d("xxw", it.message)
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }


    override val layoutId: Int = R.layout.actiivty_retrofit_layout
}