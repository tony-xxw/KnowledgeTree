package com.wynne.knowledge.tree.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wynne.knowledge.tree.R;

/**
 * @author XXW
 * @date 2018/3/6
 */

public class ClassLoaderActivity extends AppCompatActivity {
    private ClassLoader classLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classloader_activity);

        classLoader = ClassLoaderActivity.class.getClassLoader();

        while (classLoader != null) {
            Log.d("XXW", classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
