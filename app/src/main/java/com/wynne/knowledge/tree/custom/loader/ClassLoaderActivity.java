package com.wynne.knowledge.tree.custom.loader;

import android.util.Log;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;
import com.wynne.knowledge.tree.custom.tray.TrayViewBottom;
import com.wynne.knowledge.tree.custom.tray.TrayViewGroup;

/**
 * @author XXW
 * @date 2018/3/6
 */

public class ClassLoaderActivity extends BaseActivity {
    private ClassLoader classLoader;
    private TrayViewBottom trayViewBottom;
    private TrayViewGroup trayViewGroup;


    @Override
    public void initView() {
        trayViewBottom = (TrayViewBottom) findViewById(R.id.tv_bottom);
        trayViewGroup = (TrayViewGroup) findViewById(R.id.tv_group);


        trayViewBottom.setmTrayViewGroup(trayViewGroup);
        classLoader = ClassLoaderActivity.class.getClassLoader();

        while (classLoader != null) {
            Log.d("XXW", classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.classloader_activity;
    }
}
