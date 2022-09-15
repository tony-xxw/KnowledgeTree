package com.wynne.android.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.wynne.android.R
import com.wynne.android.databinding.FragmentFirstLayoutBinding
import com.wynne.knowledge.base.base.view.BaseFragment
import com.wynne.knowledge.base.bindFragment


class HomeFragment : BaseFragment() {

    companion object {
        val TAG = HomeFragment.javaClass.canonicalName
    }

    private val titles = arrayListOf("Lazy1", "Lazy2", "Lazy3")
    private val fragments =
        arrayListOf(LazyFirstFragment(), LazySecondFragment(), LazyThirdFragment())

    private val binding by lazy { FragmentFirstLayoutBinding.bind(mContentView) }

    override fun getLayoutId(): Int = R.layout.fragment_first_layout

    override fun initView() {
        binding.vpLazy.bindFragment(requireFragmentManager(), lifecycle, fragments)


        TabLayoutMediator(binding.tlBar, binding.vpLazy) { tab, position ->
            tab.text = titles[position]
        }.attach()

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("XXW", "$TAG: onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("XXW", "$TAG: onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        Log.d("XXW", "$TAG: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW", "$TAG: onPause")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("XXW", "$TAG: onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("XXW", "$TAG: onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("XXW", "$TAG: onDetach")
    }

}