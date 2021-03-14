package com.wynne.knowledge.base.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import butterknife.ButterKnife
import butterknife.Unbinder
import com.wynne.knowledge.base.R
import com.wynne.knowledge.base.utils.StatusBarUtils.Companion.adaptiveStatusBar
import com.wynne.knowledge.base.utils.StatusBarUtils.Companion.statusColor

/**
 * @author xxw
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initView()
        if (immerseModel()) {
            statusColor = ContextCompat.getColor(this, R.color.colorPrimary)
            adaptiveStatusBar(window, false)
        }
    }

    /**
     * 初始化
     *
     * @return
     */
    abstract fun initView()

    /**
     * 返回布局id
     *
     * @return
     */
    abstract val layoutId: Int

    /**
     * 是否开启沉浸模式
     *
     * @return
     */
    fun immerseModel(): Boolean {
        return true
    }

    protected inline fun <reified T : BaseActivity> Activity.startActivity() {
        startActivity(Intent(this, T::class.java))
    }
}