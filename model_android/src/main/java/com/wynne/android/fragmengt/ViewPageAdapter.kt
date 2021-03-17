package com.wynne.android.fragmengt

 import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(context: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(context) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}