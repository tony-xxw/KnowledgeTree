package com.wynne.android.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wynne.android.R
import com.wynne.android.databinding.FragmentSecondLayoutBinding
import com.wynne.android.databinding.FragmentThirdLayoutBinding
import com.wynne.knowledge.base.base.view.BaseFragment

class ThirdFragment : BaseFragment() {
    companion object {
        val TAG = ThirdFragment.javaClass.canonicalName
    }

    private val binding by lazy { FragmentThirdLayoutBinding.bind(mContentView) }

    override fun getLayoutId(): Int = R.layout.fragment_third_layout

    override fun initView() {

    }
}