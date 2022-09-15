package com.wynne.android.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wynne.android.BaseAndroidActivity
import com.wynne.android.R
import com.wynne.android.databinding.FragmentMineLayoutBinding
import com.wynne.knowledge.base.base.view.BaseFragment
import com.wynne.knowledge.base.bindAdapter

/**
 * 我的页面
 *
 * @author HB.xiangxianwen
 * @date 2022-09-15
 */
class MineFragment : BaseFragment() {

    companion object {
        val TAG = MineFragment.javaClass.canonicalName
    }

    val binding by lazy { FragmentMineLayoutBinding.bind(mContentView) }

    override fun initView() {

        binding.rvMine.bindAdapter(requireActivity(), BaseAndroidActivity.list)

    }

    override fun getLayoutId(): Int = R.layout.fragment_mine_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("XXW", "${TAG}: onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("XXW", "${TAG}: onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
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