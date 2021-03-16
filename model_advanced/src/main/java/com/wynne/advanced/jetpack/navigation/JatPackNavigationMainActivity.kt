package com.wynne.advanced.jetpack.navigation

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_advanced_jetpack_navagation_main_layout.*

class JatPackNavigationMainActivity : BaseActivity() {

    override fun initView() {
        tlBar.title = "Navigation 主页面Demo"
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = host.navController
        navigation_view.setupWithNavController(navController)
    }

    override val layoutId: Int = R.layout.activity_base_advanced_jetpack_navagation_main_layout

}