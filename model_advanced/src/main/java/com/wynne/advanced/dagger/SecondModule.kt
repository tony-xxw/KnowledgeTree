package com.wynne.advanced.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SecondModule {

    @PreActivity
    @Provides
    @Named("blue")
    fun getBlueCloth(): Cloth {
        val cloth = Cloth()
        cloth.mColor = "蓝色";
        return cloth
    }


}