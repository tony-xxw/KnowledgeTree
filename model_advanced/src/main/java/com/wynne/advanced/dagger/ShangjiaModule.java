package com.wynne.advanced.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class ShangjiaModule {

    String restaurant;


    public ShangjiaModule(String restaurant) {
        this.restaurant = restaurant;
    }


    @Provides
    public Baozi provideBaozi() {
        return new Baozi("豆沙包");
    }

    @Provides
    public Noodle provideNoodle() {
        return new Noodle();
    }

//    @Provides
//    public Noodle provideNoodle(Tongyi noodle) {
//        return noodle;
//    }

//    @Provides
//    public Noodle provideNoodle() {
//        return new KangShifu();
//    }

    @Provides
    public String provideResturant() {
        return this.restaurant;
    }

}
