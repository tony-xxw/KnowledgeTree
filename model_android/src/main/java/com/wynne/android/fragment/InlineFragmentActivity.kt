package com.wynne.android.fragment

import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.wynne.android.R
import com.wynne.knowledge.base.base.BaseActivity

class InlineFragmentActivity : BaseActivity() {
    private lateinit var beginTransaction: FragmentTransaction
    private lateinit var fragment :InlineFragment
    override fun initView() {


    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnAdd -> {
                beginTransaction = supportFragmentManager.beginTransaction()
                fragment = InlineFragment.newInstance("add")
                beginTransaction.add(R.id.flContent, fragment).commit()
            }
            R.id.btnReplace -> {
                beginTransaction = supportFragmentManager.beginTransaction()
                fragment = InlineFragment.newInstance("replace")
                beginTransaction.replace(R.id.flContent, fragment).commit()
            }
            R.id.btnRemove -> {
                beginTransaction = supportFragmentManager.beginTransaction()
                beginTransaction.remove(fragment).commit()
            }
            R.id.btnShow -> {
                beginTransaction = supportFragmentManager.beginTransaction()
                beginTransaction.show(fragment).commit()
            }
            R.id.btnHide -> {
                beginTransaction = supportFragmentManager.beginTransaction()
                beginTransaction.hide(fragment).commit()
            }
        }
    }

    override val layoutId: Int = R.layout.activity_inline_fragment_layout


}