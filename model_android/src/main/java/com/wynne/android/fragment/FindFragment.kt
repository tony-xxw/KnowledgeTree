package com.wynne.android.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wynne.android.BaseAndroidActivity
import com.wynne.android.R
import com.wynne.android.databinding.FragmentSecondLayoutBinding
import com.wynne.knowledge.base.base.view.BaseFragment
import com.wynne.knowledge.base.bindAdapter

class FindFragment : BaseFragment() {
    companion object {
        val TAG = FindFragment.javaClass.canonicalName
    }

    override fun getLayoutId(): Int = R.layout.fragment_second_layout


    private val binding by lazy { FragmentSecondLayoutBinding.bind(mContentView) }

    override fun initView() {
        binding.rvFind.bindAdapter(requireActivity(), BaseAndroidActivity.list)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("XXW", "${TAG}: onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("XXW", "${TAG}: onActivityCreated")

    }


    override fun onResume() {
        super.onResume()
        Log.d("XXW", "${TAG}: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW", "${TAG}: onPause")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("XXW", "${TAG}: onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("XXW", "${TAG}: onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("XXW", "${TAG}: onDetach")
    }
}