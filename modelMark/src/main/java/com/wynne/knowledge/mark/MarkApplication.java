package com.wynne.knowledge.mark;

import android.app.Application;

import com.wynne.knowledge.base.BaseApplication;
import com.wynne.knowledge.mark.service.MarkServiceImpl;
import com.wynne.knowledge.base.service.ServiceFactory;

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
