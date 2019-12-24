package com.wynne.other.status


import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.utils.StatusBarUtils
import com.wynne.other.R


/**
 *
 */
class StatusBarActivity : BaseActivity() {
    override fun initView() {

    }

    override fun getLayoutId(): Int = R.layout.activity_status_bar_other_layout


    fun onClick(view: View) {
        when (view.id) {
            R.id.btnCompared21 -> {
                StatusBarUtils.statusColor = ContextCompat.getColor(this, R.color.colorPrimary)
                StatusBarUtils.handleCompared21(window)
            }
            R.id.btnCompared22 -> {
                StatusBarUtils.statusColor = ContextCompat.getColor(this, R.color.colorPrimary)
                StatusBarUtils.handleCompared22(window)
            }
            R.id.btnCompared23 -> {
                StatusBarUtils.statusColor = ContextCompat.getColor(this, R.color.white)
                StatusBarUtils.handleCompared22(window)
            }
        }
    }


}
