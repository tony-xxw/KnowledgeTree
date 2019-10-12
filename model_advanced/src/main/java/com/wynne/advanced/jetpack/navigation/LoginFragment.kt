package com.wynne.advanced.jetpack.navigation

import android.util.Log
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_login_advanced.*

class LoginFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_login_advanced

    override fun initView() {
        Log.d("XXW","argument : "+ arguments?.getString("name"))
        tvAccount.text = arguments?.getString("name")
    }

}