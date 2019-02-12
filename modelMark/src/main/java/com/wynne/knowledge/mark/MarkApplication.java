package com.wynne.knowledge.mark;

import android.app.Application;
import android.util.Log;

import com.wynne.knowledge.base.BaseApplication;
import com.wynne.knowledge.mark.service.MarkServiceImpl;
import com.wynne.knowledge.base.service.ServiceFactory;

/**
 * @author xxw
 */
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
        Log.d("XXW", "测试分支");
    }

    @Override
    public void initModuleData(Application application) {

    }
}
