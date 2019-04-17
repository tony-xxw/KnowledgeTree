package com.wynne.knowledge.mark.art.chapter1

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.mark.R

/**
 * 返回home界面 再回到应用     跳到新Activity再返回原Activity
 *
 * 生命周期变化
 * onPause onStop onRestart onStar onResume
 *
 * 旋转屏幕生命周期变化
 * onPause onSaveInstanceState onStop onDestroy onStart onRestoreInstanceState onResume
 */
class ChapterOneActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.chapter_one_activity

    @Override
    override fun initView() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XXW", "ChapterOneActivity: onCreate")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("XXW", "ChapterOneActivity: onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("XXW", "ChapterOneActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("XXW", "ChapterOneActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("XXW", "ChapterOneActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("XXW", "ChapterOneActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("XXW", "ChapterOneActivity: onDestroy")
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d("XXW", "ChapterOneActivity: onSaveInstanceState")

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("XXW", "ChapterOneActivity: onRestoreInstanceState")
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.tvSample -> {
//                val intent = Intent()
//                intent.action = ChapterOneActivity;
//                intent.action = "com.wynne.chapter.two"
//                intent.addCategory("com.wynne.chapter.three")
//                intent.addCategory("com.wynne.chapter.four")
//                intent.data = Uri.fromParts("file", "", "")

                return
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        when (newConfig?.orientation) {
            1 -> {
                Log.d("XXW", "ChapterOneActivity: onConfigurationChanged 竖向")
            }
            2 -> {
                Log.d("XXW", "ChapterOneActivity: onConfigurationChanged 横向")
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("XXW", "onNewIntent");
    }
}