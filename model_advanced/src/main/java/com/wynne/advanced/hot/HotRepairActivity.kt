package com.wynne.advanced.hot

import android.os.Environment
import android.widget.Toast
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base_advanced_hot_repair_layout.*

class HotRepairActivity : BaseActivity() {
    override fun initView() {
        btnBug.setOnClickListener {
            Toast.makeText(this, "1".toInt(), Toast.LENGTH_LONG).show()
//            Toast.makeText(this, "修复成功", Toast.LENGTH_LONG).show()
        }
        btnFix.setOnClickListener {
            FixDexUtils.loadFixedDex(this, Environment.getExternalStorageDirectory())
        }
    }

    override val layoutId: Int = R.layout.activity_base_advanced_hot_repair_layout
}