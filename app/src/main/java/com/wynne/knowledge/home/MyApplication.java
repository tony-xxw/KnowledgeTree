package com.wynne.knowledge.home;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wynne.knowledge.base.BaseApplication;
import com.wynne.knowledge.base.constant.AppConfig;

/**
 * 主Application
 *
 * @author xxw
 */
public class MyApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebug()) {
            ARouter.openLog();

            ARouter.openDebug();
        }

        ARouter.init(this);

        initModuleApp(this);
        initModuleData(this);

    }


    private boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void initModuleApp(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApplication baseApplication = (BaseApplication) clazz.newInstance();
                baseApplication.initModuleApp(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initModuleData(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApplication baseApplication = (BaseApplication) clazz.newInstance();
                baseApplication.initModuleData(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
