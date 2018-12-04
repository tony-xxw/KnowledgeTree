package com.wynne.knowledge.tree;

import android.app.Application;

import com.wynne.knowledge.tree.service.GuideServiceImpl;
import com.wynne.knowledge.tree.service.ServiceFactory;

/**
 * @author Wynne
 */
public class GuideApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setAccountService(new GuideServiceImpl());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
