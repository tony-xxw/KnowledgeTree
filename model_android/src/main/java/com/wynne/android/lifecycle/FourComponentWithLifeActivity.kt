package com.wynne.android.lifecycle

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.wynne.android.BaseAndroidActivity
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.actiivty_life_layout.*

class FourComponentWithLifeActivity : BaseActivity() {
    lateinit var presenter: IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XXW", "onCreate: FourComponentWithLifeActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("XXW", "onStart: FourComponentWithLifeActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("XXW", "onResume: FourComponentWithLifeActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("XXW", "onRestart: FourComponentWithLifeActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW", "onPause: FourComponentWithLifeActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("XXW", "onStop: FourComponentWithLifeActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("XXW", "onDestroy: FourComponentWithLifeActivity")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("XXW", "onRestoreInstanceState: FourComponentWithLifeActivity")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("XXW", "onConfigurationChanged: FourComponentWithLifeActivity")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("XXW", "onNewIntent: FourComponentWithLifeActivity")
    }

    override fun initView() {
        presenter = LifePresenter()
        lifecycle.addObserver(presenter)


        btnActivity.setOnClickListener {
            startActivity(Intent(this, BaseAndroidActivity::class.java))
        }
    }

    override val layoutId: Int = R.layout.actiivty_life_layout


}