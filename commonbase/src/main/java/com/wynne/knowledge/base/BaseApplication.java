package com.wynne.knowledge.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * @author xxw
 */
public abstract class BaseApplication extends MultiDexApplication {

    private ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

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
