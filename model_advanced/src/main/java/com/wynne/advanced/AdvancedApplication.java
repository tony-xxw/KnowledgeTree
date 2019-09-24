package com.wynne.advanced;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.google.android.material.appbar.AppBarLayout;
import com.wynne.knowledge.base.BaseApplication;

public class AdvancedApplication extends BaseApplication {
    private static AdvancedApplication advancedApplication;

    @Override
    public void initModuleApp(Application application) {

    }

    @Override
    public void initModuleData(Application application) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        advancedApplication = this;
    }

    public static Application getInstance() {
        return advancedApplication;
    }


}
