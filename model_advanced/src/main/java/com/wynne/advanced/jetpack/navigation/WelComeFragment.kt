package com.wynne.advanced.jetpack.navigation

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_welcome_advanced.*

class WelComeFragment : BaseFragment() {
    override fun initView() {
        btnLogin.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", "Wynne")

            findNavController().navigate(R.id.login, bundle)
        }

        btnRegister.setOnClickListener {
            val action = WelComeFragmentDirections.actionWelcomeToRegister()
            findNavController().navigate(action)
        }

        btnMain.setOnClickListener {
            startActivity(Intent(activity, JatPackNavigationMainActivity::class.java))
        }


    }


    override fun getLayoutId(): Int = R.layout.fragment_welcome_advanced


}