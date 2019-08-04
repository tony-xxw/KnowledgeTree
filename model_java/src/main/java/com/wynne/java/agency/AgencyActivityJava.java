package com.wynne.java.agency;

import android.util.Log;

import com.wynne.java.R;
import com.wynne.knowledge.base.base.BaseActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AgencyActivityJava extends BaseActivity {
    @Override
    public void initView() {
        Subject subject = new RealSubject();
        InvocationHandler invocationHandler = new DynticProxys(subject);

        Subject subject1 = (Subject) Proxy.newProxyInstance(
                invocationHandler.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                invocationHandler);

        Log.d("XXW", "main " + subject1.getClass().getName());
        subject1.rent();
        subject1.hello("world");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_agency_layout;
    }
}
