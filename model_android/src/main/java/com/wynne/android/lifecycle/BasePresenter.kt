package com.wynne.android.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

open class BasePresenter : IPresenter {


    override fun onCreate(owner: LifecycleOwner) {
        Log.d("XXW1", "BasePresenter.onCreate " + this.javaClass.toString())
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d("XXW1", "BasePresenter.onDestroy " + this.javaClass.toString())
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("XXW1", "BasePresenter.onLifecycleChanged ")
    }
}