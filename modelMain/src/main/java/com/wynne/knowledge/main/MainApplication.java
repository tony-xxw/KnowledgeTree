package com.wynne.knowledge.main;

import android.app.Application;

import com.wynne.knowledge.base.BaseApplication;
import com.wynne.knowledge.main.service.MainServiceImpl;
import com.wynne.knowledge.base.service.ServiceFactory;

/**
 * Main
 *
 * @author xxw
 */
public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);

    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setAccountService(new MainServiceImpl());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
