package com.wynne.knowledge.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.wynne.knowledge.base.adapter.DefaultAdapter
import com.wynne.knowledge.base.adapter.MainData

/**
 * TODO 类描述
 *
 * @author HB.xiangxianwen
 * @date 2022-09-14
 */


fun ViewPager2.bindFragment(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    fragments: List<Fragment>
): ViewPager2 {

    adapter = object : FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
    return this
}

fun RecyclerView.bindAdapter(
    context: Context,
    list: List<MainData>,
    block: (position: Int) -> Unit = {}
) {
    layoutManager = LinearLayoutManager(context)
    adapter = DefaultAdapter(context, list).apply {
        listener = block
    }

}
