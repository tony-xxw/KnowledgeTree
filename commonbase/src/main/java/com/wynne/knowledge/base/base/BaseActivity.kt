package com.wynne.knowledge.base.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.wynne.knowledge.base.R
import com.wynne.knowledge.base.utils.StatusBarUtils.Companion.adaptiveStatusBar
import com.wynne.knowledge.base.utils.StatusBarUtils.Companion.statusColor
import java.util.zip.Inflater

/**
 * @author xxw
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = LayoutInflater.from(this).inflate(layoutId, null)
        setContentView(root)
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