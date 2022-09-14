package com.wynne.android.fragment

import android.os.Bundle
import android.util.Log
import android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wynne.android.R
import com.wynne.android.bean.MenuItem
import com.wynne.android.bindFragment
import com.wynne.android.databinding.ActiivtyFragmentLayoutBinding
import com.wynne.knowledge.base.base.BaseActivity

class AndroidFragmentActivity : BaseActivity(), ViewPager2.PageTransformer {
    companion object {
        val TAG = AndroidFragmentActivity.javaClass.canonicalName
    }

    lateinit var text: String

    private val titles = arrayListOf("First", "Second", "Third")

    private val fragments = arrayListOf(FirstFragment(), SecondFragment(), ThirdFragment())

    private val binding by lazy { ActiivtyFragmentLayoutBinding.bind(root) }

    override fun initView() {


        binding.vpFragment.bindFragment(supportFragmentManager, lifecycle, fragments)

        TabLayoutMediator(binding.tlBLayout, binding.vpFragment) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override val layoutId: Int = R.layout.actiivty_fragment_layout

    override fun transformPage(page: View, position: Float) {

    }

    val menus = arrayOf(
        MenuItem("首页", R.drawable.ic_home),
        MenuItem("发现", R.drawable.ic_find),
        MenuItem("匹配", R.drawable.ic_matching),
        MenuItem("我的", R.drawable.ic_mine),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        val nanoTime = System.nanoTime()
        Log.d("XXW", "stat: $nanoTime")
        super.onCreate(savedInstanceState)
        Log.d("XXW", "end: ${System.nanoTime() - nanoTime}")
        Log.d(TAG, "onCreate")


        menus.forEachIndexed { index, item ->
            binding.bnvNavigation.menu.add(item.title).setShowAsAction(SHOW_AS_ACTION_IF_ROOM)
            binding.bnvNavigation.menu.getItem(index).setIcon(item.icon)
        }
//        binding.bnvNavigation.shi

    }


}