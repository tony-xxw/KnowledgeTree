package com.wynne.android.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.actiivty_fragment_layout.*

class AndroidFragmentActivity : BaseActivity(), ViewPager2.PageTransformer {
    companion object {
        val TAG = AndroidFragmentActivity.javaClass.canonicalName
    }

    lateinit var text: String

    val adapter: ViewPageAdapter by lazy {
        ViewPageAdapter(this, arrayListOf<Fragment>(FirstFragment(), SecondFragment(), ThirdFragment()))
    }
    val titles: List<String> by lazy {
        arrayListOf(
                "First", "Second", "Third"
        )
    }

    override fun initView() {

        vpFragment.adapter = adapter

        TabLayoutMediator(tlBLayout, vpFragment) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override val layoutId: Int = R.layout.actiivty_fragment_layout
    override fun transformPage(page: View, position: Float) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}