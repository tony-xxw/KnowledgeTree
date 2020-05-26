package com.wynne.advanced;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.google.android.material.appbar.AppBarLayout;
import com.wynne.advanced.dagger.base.BaseComponent;
import com.wynne.advanced.dagger.base.DaggerBaseComponent;
import com.wynne.knowledge.base.BaseApplication;

public class AdvancedApplication extends BaseApplication {
    private static AdvancedApplication advancedApplication;

    public BaseComponent baseComponent;


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

        baseComponent = DaggerBaseComponent.builder().build();
    }

    public static AdvancedApplication getInstance() {
        return advancedApplication;
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }

}
