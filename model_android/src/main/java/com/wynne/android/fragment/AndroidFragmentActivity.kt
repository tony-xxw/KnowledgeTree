package com.wynne.android.fragment

import android.os.Bundle
import android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wynne.android.R
import com.wynne.android.bean.MenuItem
import com.wynne.knowledge.base.bindFragment
import com.wynne.android.databinding.ActiivtyFragmentLayoutBinding
import com.wynne.knowledge.base.base.BaseActivity

class AndroidFragmentActivity : BaseActivity(), ViewPager2.PageTransformer {
    companion object {
        val TAG = AndroidFragmentActivity.javaClass.canonicalName
    }

    lateinit var text: String


    private val fragments =
        arrayListOf(HomeFragment(), FindFragment(), MatchFragment(), MineFragment())

    private val binding by lazy { ActiivtyFragmentLayoutBinding.bind(root) }

    override fun initView() {


        binding.vpFragment.bindFragment(supportFragmentManager, lifecycle, fragments)
    }

    override val layoutId: Int = R.layout.actiivty_fragment_layout

    override fun transformPage(page: View, position: Float) {

    }

    private val menus = arrayOf(
        MenuItem("首页", R.drawable.shape_home_seleted),
        MenuItem("发现", R.drawable.shape_find_seleted),
        MenuItem("匹配", R.drawable.shape_match_seleted),
        MenuItem("我的", R.drawable.shape_mine_seleted),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        menus.forEachIndexed { index, item ->
            binding.bnvNavigation.menu.add(item.title).setShowAsAction(SHOW_AS_ACTION_IF_ROOM)
            binding.bnvNavigation.menu.getItem(index).setIcon(item.icon)
        }
        binding.bnvNavigation.itemIconTintList =null
        binding.vpFragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bnvNavigation.menu.getItem(position).isChecked = true
            }
        })
        binding.bnvNavigation.setOnNavigationItemSelectedListener {
            when (it.title) {
                "首页" -> {
                    binding.vpFragment.currentItem = 0
                }
                "发现" -> {
                    binding.vpFragment.currentItem = 1
                }
                "匹配" -> {
                    binding.vpFragment.currentItem = 2
                }
                "我的" -> {
                    binding.vpFragment.currentItem = 3
                }
            }
            true
        }

    }


}