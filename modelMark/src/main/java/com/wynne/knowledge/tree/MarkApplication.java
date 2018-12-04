package com.wynne.knowledge.tree;

import android.app.Application;

import com.wynne.knowledge.tree.service.MarkServiceImpl;
import com.wynne.knowledge.tree.service.ServiceFactory;

public class MarkApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setAccountService(new MarkServiceImpl());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
