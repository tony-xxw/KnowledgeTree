package com.wynne.knowledge.mark.activity;

import android.view.View;


import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

/**
 * Activity 常用的各种标记
 * FLAG_ACTIVITY_NEW_TASK
 * FLAG_ACTIVITY_SINGLE_TOP
 * FLAG_ACTIVITY_CLEAR_TOP
 * FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS  此Activity不加入栈中
 * <p>
 * <p>
 * Filter匹配规则
 * action 当且仅当只需要一个action与filter匹配则可以完成
 * category 必须加上DEFAULT才能与filter匹配 其他与action差不多
 * date 分url与minetype
 * <p>
 * minetype 一般指媒体类型 如image/jpeg, audio/mpeg4-generic 和video/*等不同媒体格式
 * url  3/16完成
 *
 * @author Wynne
 * @date 2018/3/15
 */

public class FlagsActivity extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.flags_activity;
    }


    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_flags) {
        } else {
        }
    }
}
