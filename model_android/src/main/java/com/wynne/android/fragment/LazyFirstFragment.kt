package com.wynne.android.fragment

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.wynne.android.R
import com.wynne.android.databinding.FragmentFirstLazyLayoutBinding
import com.wynne.knowledge.base.base.view.BaseFragment
import com.wynne.knowledge.base.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * TODO 类描述
 *
 * @author HB.xiangxianwen
 * @date 2022-09-15
 */
class LazyFirstFragment : BaseFragment() {

    val binding by lazy { FragmentFirstLazyLayoutBinding.bind(mContentView) }
    override fun initView() {

//        lazyInfalte()
    }

    override fun getLayoutId(): Int = R.layout.fragment_first_lazy_layout

    override fun onResume() {
        super.onResume()
        Log.d("XXW", "LazyFirst onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW", "LazyFirst onPause")
    }

    fun lazyInfalte() {
        lifecycleScope.launch {

            val start = System.currentTimeMillis()

            val rootView = async(Dispatchers.IO) {
                LogUtil.d("开始异步加载真正的跟视图")

                val view = binding.vsLazy.inflate()
                val end = System.currentTimeMillis()

                LogUtil.d("加载真正布局耗时：" + (end - start))

                view
            }

            val request = async {
                LogUtil.d("开始请求用户详情数据")
                delay(1500)
                true
            }

            if (request.await() && rootView.await() != null) {
                val start1 = System.currentTimeMillis()
                binding.flContent.addView(rootView.await(), 0)
                val end1 = System.currentTimeMillis()
                LogUtil.d("添加布局耗时：" + (end1 - start1))
            }

        }
    }
}