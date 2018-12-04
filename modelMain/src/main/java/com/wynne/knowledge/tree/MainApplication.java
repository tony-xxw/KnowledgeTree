package com.wynne.knowledge.tree;

import android.app.Application;

import com.wynne.knowledge.tree.service.MainServiceImpl;
import com.wynne.knowledge.tree.service.ServiceFactory;

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
