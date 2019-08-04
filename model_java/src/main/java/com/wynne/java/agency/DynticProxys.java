package com.wynne.java.agency;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynticProxys implements InvocationHandler {
    private Object object;

    public DynticProxys(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d("XXW", "before rent house");
        Log.d("XXW", "method: " + method);

        method.invoke(object, args);
        Log.d("XXW", "after rent house");
        return null;
    }
}
