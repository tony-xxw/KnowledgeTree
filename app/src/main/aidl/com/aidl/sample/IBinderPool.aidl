// IBinderPool.aidl
package com.aidl.sample;

// Declare any non-default types here with import statements

interface IBinderPool {

    IBinder queryBinder(int binderCode);
}
