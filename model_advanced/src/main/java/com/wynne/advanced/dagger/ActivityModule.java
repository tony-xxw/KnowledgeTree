package com.wynne.advanced.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    int provideActivityTest() {
        return 123456;
    }
}
