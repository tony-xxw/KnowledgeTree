package com.wynne.knowledge.tree;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * @author xxw
 */
public abstract class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }

    /**
     * 各个组件初始化
     *
     * @param application
     */
    public abstract void initModuleApp(Application application);

    /**
     * 各组件初始化数据
     *
     * @param application
     */
    public abstract void initModuleData(Application application);

}
