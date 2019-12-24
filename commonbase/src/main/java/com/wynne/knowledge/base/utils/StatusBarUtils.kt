package com.wynne.knowledge.base.utils

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.*
import android.widget.FrameLayout

/**
 * 状态栏设置
 * ->  不同系统版本的状态栏设置都会有区别,主要有3个版本的区分(4.4,5.1,6.0+)
 */
class StatusBarUtils {

    companion object {

        var statusColor = Color.parseColor("#35bcdc")


        fun handleCompared21(window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //4.4.2设置系统状态栏为半透明
                //AndroidManifest.xml <activity>中设置 windowTranslucentStatus = true 即可
                //java代码如下
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

                val decorView = window.decorView as ViewGroup
                val statusView = View(window.context)
                val statusBarHeight = obtainStatusBarHeight(window.context)
                val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight)
                layoutParams.gravity = Gravity.TOP
                statusView.layoutParams = layoutParams
                statusView.setBackgroundColor(statusColor)
                decorView.addView(statusView)
            }

            //设置完之后 状态会盖住布局,需要给状态栏滞留空间 给root布局设置fitsSystemWindows = true即可
            //代码设置

            val viewGroup = window.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
            val childView = viewGroup.getChildAt(0)
            childView?.let {
                childView.fitsSystemWindows = true
            }
        }


        fun handleCompared22(window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = statusColor
            }
        }

        fun handleCompared23(window: Window, isWhite: Boolean = false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = statusColor

                val decorView = window.decorView
                var ui = decorView.systemUiVisibility
                ui = if (isWhite) {
                    ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    ui and (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())
                }
                decorView.systemUiVisibility = ui

            }
        }


        private fun obtainStatusBarHeight(context: Context): Int {
            var statusBarHeight = 0
            var resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
            }
            return statusBarHeight
        }

        fun adaptiveStatusBar(window: Window, isWhite: Boolean = false) {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP -> {
                    handleCompared21(window)
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M -> {
                    handleCompared22(window)
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                    handleCompared23(window, isWhite)
                }
            }
        }
    }


}
