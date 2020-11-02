package com.wynne.other

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath
import com.wynne.other.coroutine.CoroutineActivity
import com.wynne.other.edit.OtherInputEditActivity
import com.wynne.other.grammar.KotlinGrammarActivity
import com.wynne.other.kotlin.KotlinCoreActivity
import com.wynne.other.status.StatusBarActivity
import com.wynne.other.web.WebViewActivity
import io.flutter.embedding.android.FlutterActivity

@Route(path = ARouterPath.BASE_OTHER)
class BaseOtherActivity : BaseActivity() {
    override fun initView() {
    }

    override fun getLayoutId(): Int = R.layout.activity_base_other_layout

    fun onClick(v: View) {
        when (v.id) {
            R.id.btnEditInput -> {
                startActivity(Intent(this, OtherInputEditActivity::class.java))
            }
            R.id.btnStatusBar -> {
                startActivity(Intent(this, StatusBarActivity::class.java))
            }
            R.id.btnGrammar -> {
                startActivity(Intent(this, KotlinGrammarActivity::class.java))
            }
            R.id.btnWebView -> {
                startActivity(Intent(this, WebViewActivity::class.java))
            }
            R.id.btnKotlinCoroutine -> {
                startActivity(Intent(this, CoroutineActivity::class.java))
            }
            R.id.btnFlutter -> {
                startActivity(
                        FlutterActivity.createDefaultIntent(this)
                )
            }
            R.id.btnCore -> {
                startActivity(Intent(this, KotlinCoreActivity::class.java))
            }

        }
    }
}