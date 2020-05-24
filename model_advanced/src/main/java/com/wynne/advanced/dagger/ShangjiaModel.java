package com.wynne.advanced.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class ShangjiaModel {
    @Provides
    public Baozi provideBaozi() {
        return new Baozi("豆沙包");
    }

    @Provides
    public Noodle provideNoodle() {
        return new Noodle();
    }

}
