package con.wynne.thread

import com.alibaba.android.arouter.facade.annotation.Route
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.constant.ARouterPath.BASE_THREAD
import kotlinx.android.synthetic.main.activity_base_thread_layout.*

@Route(path = BASE_THREAD)
class BaseThreadActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_thread_layout

    override fun initView() {
        tlBar.title="异步/线程"
    }
}