package com.wynne.knowledge.tree.custom.loader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.custom.tray.TrayViewBottom;
import com.wynne.knowledge.tree.custom.tray.TrayViewGroup;

/**
 * @author XXW
 * @date 2018/3/6
 */

public class ClassLoaderActivity extends AppCompatActivity {
    private ClassLoader classLoader;
    private TrayViewBottom trayViewBottom;
    private TrayViewGroup trayViewGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classloader_activity);

        trayViewBottom = (TrayViewBottom) findViewById(R.id.tv_bottom);
        trayViewGroup = (TrayViewGroup) findViewById(R.id.tv_group);


        trayViewBottom.setmTrayViewGroup(trayViewGroup);
        classLoader = ClassLoaderActivity.class.getClassLoader();

        while (classLoader != null) {
            Log.d("XXW", classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
