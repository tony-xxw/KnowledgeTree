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
//            通过id跳转
            val bundle = Bundle()
            bundle.putString("name", "Wynne")
            findNavController().navigate(R.id.login,bundle)

//            通过safe Args传参
//            val action = WelComeFragmentDirections.actionWelcomeToLogin()
//            action.name="Wynne"
//            findNavController().navigate(action)



        }

        btnRegister.setOnClickListener {
            val action = WelComeFragmentDirections.actionWelcomeToRegister()
            findNavController().navigate(action)
        }

        btnMain.setOnClickListener {
            startActivity(Intent(activity, JatPackNavigationMainActivity::class.java))
        }

        btnWelCome.setOnClickListener {
            //跳转嵌套导航
            findNavController().navigate(R.id.nesting)
        }

        btnGlobal.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", "Wynne")
            //通过全局action跳转
            findNavController().navigate(R.id.action_global_login,bundle)
        }

    }


    override fun getLayoutId(): Int = R.layout.fragment_welcome_advanced


}