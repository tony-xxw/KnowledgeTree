package com.wynne.android.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wynne.android.R
import com.wynne.android.databinding.ActiivtyFragmentLayoutBinding
import com.wynne.android.databinding.FragmentFirstLayoutBinding
import com.wynne.knowledge.base.base.view.BaseFragment


class FirstFragment : BaseFragment() {

    companion object {
        val TAG = FirstFragment.javaClass.canonicalName
    }

    private val binding by lazy { FragmentFirstLayoutBinding.bind(mContentView) }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    var text = "smaple"

    lateinit var activity: AndroidFragmentActivity
    override fun getLayoutId(): Int = R.layout.fragment_first_layout

    override fun initView() {
        binding.tvFirst.setOnClickListener {
            startActivity(Intent(requireActivity(), InlineFragmentActivity::class.java))
        }

        activity.text = text
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = (context) as AndroidFragmentActivity
        Log.d(TAG, "onAttach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")

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

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }

}