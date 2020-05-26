package com.wynne.advanced.dagger

import android.util.Log
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    fun getCloth(): Cloth {
        return Cloth()
    }

    @PreActivity
    @Provides
    @Named("red")
    fun getRedCloth(): Cloth {
        val cloth = Cloth()
        cloth.mColor = "红色"
        return cloth
    }

    @Provides
    @Named("blue")
    fun getBlueCloth(): Cloth {
        val cloth = Cloth()
        cloth.mColor = "蓝色";
        return cloth
    }

    @Provides
    fun getSbf(): StringBuffer {
        return StringBuffer("实例化StringBuffer")
    }

    @Provides
    fun getCloths(@Named("red") cloth: Cloth): Cloths {
        return Cloths(cloth)
    }

}